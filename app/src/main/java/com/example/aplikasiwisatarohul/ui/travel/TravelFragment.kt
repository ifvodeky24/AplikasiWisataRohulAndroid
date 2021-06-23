package com.example.aplikasiwisatarohul.ui.travel

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
import com.example.aplikasiwisatarohul.adapter.TravelAdapter
import com.example.aplikasiwisatarohul.data.source.remote.response.Travel
import com.example.aplikasiwisatarohul.databinding.FragmentTravelBinding
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory

class TravelFragment : Fragment(), TravelCallback {

    private var _binding: FragmentTravelBinding? = null
    private val binding get() = _binding
    private var viewModel: TravelViewModel? = null
    private lateinit var travelAdapter: TravelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTravelBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TravelViewModel::class.java]

        travelAdapter = TravelAdapter(this@TravelFragment)

        viewModel?.getAllTravel()?.observe(viewLifecycleOwner, { travel ->
            when (travel.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    travelAdapter.setTravel(travel.data)
                    travelAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding?.progressBar?.isVisible
                }

                Status.ERROR -> {
                    binding?.progressBar?.gone()
                    Toast.makeText(
                        context,
                        "Terjadi kesalahan ${travel.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        with(binding?.rvTravel) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = travelAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: Travel) {
        val moveWithObjectIntent = Intent(activity, DetailTravelActivity::class.java)
        moveWithObjectIntent.putExtra(DetailTravelActivity.DATA, data)
        startActivity(moveWithObjectIntent)
    }

}