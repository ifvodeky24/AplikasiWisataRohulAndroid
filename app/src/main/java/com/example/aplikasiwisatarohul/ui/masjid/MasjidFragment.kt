package com.example.aplikasiwisatarohul.ui.masjid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.adapter.EventAdapter
import com.example.aplikasiwisatarohul.adapter.MasjidAdapter
import com.example.aplikasiwisatarohul.databinding.FragmentEventBinding
import com.example.aplikasiwisatarohul.databinding.FragmentMasjidBinding
import com.example.aplikasiwisatarohul.ui.event.EventViewModel
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory

class MasjidFragment : Fragment() , MasjidCallback{

    private var _binding: FragmentMasjidBinding? = null
    private val binding get() = _binding
    private var viewModel: MasjidViewModel? = null
    private lateinit var masjidAdapter: MasjidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMasjidBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MasjidViewModel::class.java]

        masjidAdapter = MasjidAdapter(this@MasjidFragment)

        viewModel?.getAllMasjid()?.observe(viewLifecycleOwner, { masjid ->
            when (masjid.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    masjidAdapter.setMasjid(masjid.data)
                    masjidAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding?.progressBar?.isVisible
                }

                Status.ERROR -> {
                    binding?.progressBar?.gone()
                    Toast.makeText(context, "Terjadi kesalahan ${masjid.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        with(binding?.rvMasjid) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = masjidAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id_masjid: String) {

    }
}