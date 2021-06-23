package com.example.aplikasiwisatarohul.ui.penginapan

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
import com.example.aplikasiwisatarohul.adapter.PenginapanAdapter
import com.example.aplikasiwisatarohul.data.source.remote.response.Penginapan
import com.example.aplikasiwisatarohul.databinding.FragmentPenginapanBinding
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory

class PenginapanFragment : Fragment(), PenginapanCallback {

    private var _binding: FragmentPenginapanBinding? = null
    private val binding get() = _binding
    private var viewModel: PenginapanViewModel? = null
    private lateinit var penginapanAdapter: PenginapanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPenginapanBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[PenginapanViewModel::class.java]

        penginapanAdapter = PenginapanAdapter(this@PenginapanFragment)

        viewModel?.getAllPenginapan()?.observe(viewLifecycleOwner, { penginapan ->
            when (penginapan.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    penginapanAdapter.setPenginapan(penginapan.data)
                    penginapanAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding?.progressBar?.isVisible
                }

                Status.ERROR -> {
                    binding?.progressBar?.gone()
                    Toast.makeText(
                        context,
                        "Terjadi kesalahan ${penginapan.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        with(binding?.rvPenginapan) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = penginapanAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: Penginapan) {
        val moveWithObjectIntent = Intent(activity, DetailPenginapanActivity::class.java)
        moveWithObjectIntent.putExtra(DetailPenginapanActivity.DATA, data)
        startActivity(moveWithObjectIntent)
    }
}