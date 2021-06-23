package com.example.aplikasiwisatarohul.ui.berita

import android.content.Intent
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
import com.example.aplikasiwisatarohul.adapter.BeritaAdapter
import com.example.aplikasiwisatarohul.data.source.remote.response.Berita
import com.example.aplikasiwisatarohul.databinding.FragmentAtmBinding
import com.example.aplikasiwisatarohul.databinding.FragmentBeritaBinding
import com.example.aplikasiwisatarohul.ui.atm.AtmViewModel
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory

class BeritaFragment : Fragment() , BeritaCallback{

    private var _binding: FragmentBeritaBinding? = null
    private val binding get() = _binding
    private var viewModel: BeritaViewModel? = null
    private lateinit var beritaAdapter: BeritaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBeritaBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[BeritaViewModel::class.java]

        beritaAdapter = BeritaAdapter(this@BeritaFragment)

        viewModel?.getAllBerita()?.observe(viewLifecycleOwner, { atm ->
            when (atm.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    beritaAdapter.setAtm(atm.data)
                    beritaAdapter.notifyDataSetChanged()
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

        with(binding?.rvBerita) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = beritaAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: Berita) {
        val moveWithObjectIntent = Intent(activity, DetailBeritaActivity::class.java)
        moveWithObjectIntent.putExtra(DetailBeritaActivity.DATA, data)
        startActivity(moveWithObjectIntent)
    }

}