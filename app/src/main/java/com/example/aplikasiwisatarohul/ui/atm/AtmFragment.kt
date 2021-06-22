package com.example.aplikasiwisatarohul.ui.atm

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
import com.example.aplikasiwisatarohul.adapter.AtmAdapter
import com.example.aplikasiwisatarohul.adapter.MasjidAdapter
import com.example.aplikasiwisatarohul.databinding.FragmentAtmBinding
import com.example.aplikasiwisatarohul.databinding.FragmentMasjidBinding
import com.example.aplikasiwisatarohul.ui.masjid.MasjidViewModel
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory

class AtmFragment : Fragment(), AtmCallback {

    private var _binding: FragmentAtmBinding? = null
    private val binding get() = _binding
    private var viewModel: AtmViewModel? = null
    private lateinit var atmAdapter: AtmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAtmBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[AtmViewModel::class.java]

        atmAdapter = AtmAdapter(this@AtmFragment)

        viewModel?.getAllAtm()?.observe(viewLifecycleOwner, { atm ->
            when (atm.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    atmAdapter.setAtm(atm.data)
                    atmAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding?.progressBar?.isVisible
                }

                Status.ERROR -> {
                    binding?.progressBar?.gone()
                    Toast.makeText(context, "Terjadi kesalahan ${atm.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        with(binding?.rvAtm) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = atmAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(id_atm: String) {

    }

}