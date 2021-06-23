package com.example.aplikasiwisatarohul.ui.event

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
import com.example.aplikasiwisatarohul.adapter.EventAdapter
import com.example.aplikasiwisatarohul.data.source.remote.response.Event
import com.example.aplikasiwisatarohul.databinding.FragmentEventBinding
import com.example.aplikasiwisatarohul.databinding.FragmentHomeBinding
import com.example.aplikasiwisatarohul.ui.berita.DetailBeritaActivity
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory

class EventFragment : Fragment() , EventCallback{

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding
    private var viewModel: EventViewModel? = null
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEventBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java]

        eventAdapter = EventAdapter(this@EventFragment)

        viewModel?.getAllEvent()?.observe(viewLifecycleOwner, { event ->
            when (event.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    eventAdapter.setEvent(event.data)
                    eventAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding?.progressBar?.isVisible
                }

                Status.ERROR -> {
                    binding?.progressBar?.gone()
                    Toast.makeText(context, "Terjadi kesalahan ${event.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        with(binding?.rvEvent) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = eventAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: Event) {
        val moveWithObjectIntent = Intent(activity, DetailEventActivity::class.java)
        moveWithObjectIntent.putExtra(DetailEventActivity.DATA, data)
        startActivity(moveWithObjectIntent)
    }


}