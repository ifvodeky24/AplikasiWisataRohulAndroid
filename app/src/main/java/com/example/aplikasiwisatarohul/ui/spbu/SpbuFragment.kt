package com.example.aplikasiwisatarohul.ui.spbu

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
import com.example.aplikasiwisatarohul.adapter.SpbuAdapter
import com.example.aplikasiwisatarohul.data.source.remote.response.Spbu
import com.example.aplikasiwisatarohul.databinding.FragmentSpbuBinding
import com.example.aplikasiwisatarohul.ui.masjid.DetailMasjidActivity
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory
class SpbuFragment : Fragment(), SpbuCallback {

    private var _binding: FragmentSpbuBinding? = null
    private val binding get() = _binding
    private var viewModel: SpbuViewModel? = null
    private lateinit var spbuAdapter: SpbuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpbuBinding.inflate(inflater, container, false)

        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[SpbuViewModel::class.java]

        spbuAdapter = SpbuAdapter(this@SpbuFragment)

        viewModel?.getAllSpbu()?.observe(viewLifecycleOwner, { atm ->
            when (atm.status) {
                Status.SUCCESS -> {
                    binding?.progressBar?.gone()
                    spbuAdapter.setAtm(atm.data)
                    spbuAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    binding?.progressBar?.isVisible
                }

                Status.ERROR -> {
                    binding?.progressBar?.gone()
                    Toast.makeText(context, "Terjadi kesalahan ${atm.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        with(binding?.rvSpbu) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = spbuAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(data: Spbu) {
        val moveWithObjectIntent = Intent(activity, DetailSpbuActivity::class.java)
        moveWithObjectIntent.putExtra(DetailSpbuActivity.DATA, data)
        startActivity(moveWithObjectIntent)
    }
}