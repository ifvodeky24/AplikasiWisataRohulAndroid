package com.example.aplikasiwisatarohul.ui.wisata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiwisatarohul.adapter.WisataAdapter
import com.example.aplikasiwisatarohul.data.source.remote.response.Wisata
import com.example.aplikasiwisatarohul.databinding.ActivityWisataBinding
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import com.example.aplikasiwisatarohul.utils.gone
import com.example.aplikasiwisatarohul.utils.visible
import com.example.aplikasiwisatarohul.vo.Status
import com.example.aplikasiwisatarohul.vo.ViewModelFactory
import timber.log.Timber

class WisataActivity : AppCompatActivity(), WisataCallback {

    companion object {
        const val NAME = "name"
    }

    private lateinit var binding: ActivityWisataBinding
    private var viewModel: WisataViewModel? = null
    private lateinit var wisataAdapter: WisataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Wisata"
        binding = ActivityWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(NAME)

        Timber.d("cek name $name")

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[WisataViewModel::class.java]

        wisataAdapter = WisataAdapter(this)

        viewModel?.getAllWisata()?.observe(this, { wisata ->
            when (wisata.status) {
                Status.SUCCESS -> {
                    when (name) {
                        "kuliner" -> {
                            val dataKuliner =
                                wisata.data?.filter { a -> a.kategori == "Wisata Kuliner" }
                            if (dataKuliner != null) {
                                if (dataKuliner.isNotEmpty()) {
                                    binding.progressBar.gone()
                                    wisataAdapter.setWisata(dataKuliner)
                                    wisataAdapter.notifyDataSetChanged()
                                } else {
                                    binding.llEmptyList.visible()
                                    binding.rvWisata.gone()
                                }
                            } else {
                                binding.llEmptyList.visible()
                                binding.rvWisata.gone()
                            }
                        }
                        "sejarah" -> {
                            val dataSejarah =
                                wisata.data?.filter { a -> a.kategori == "Wisata Sejarah" }
                            if (dataSejarah != null) {
                                if (dataSejarah.isNotEmpty()) {
                                    binding.progressBar.gone()
                                    wisataAdapter.setWisata(dataSejarah)
                                    wisataAdapter.notifyDataSetChanged()
                                } else {
                                    binding.llEmptyList.visible()
                                    binding.rvWisata.gone()
                                }
                            } else {
                                binding.llEmptyList.visible()
                                binding.rvWisata.gone()
                            }
                        }
                        "buatan" -> {
                            val dataBuatan =
                                wisata.data?.filter { a -> a.kategori == "Wisata Buatan" }
                            Timber.d("hehehehhe $dataBuatan")
                            if (dataBuatan != null) {
                                if (dataBuatan.isNotEmpty()) {
                                    binding.progressBar.gone()
                                    wisataAdapter.setWisata(dataBuatan)
                                    wisataAdapter.notifyDataSetChanged()
                                } else {
                                    Timber.d("kuuyy")
                                    binding.llEmptyList.visible()
                                    binding.rvWisata.gone()
                                }
                            } else {
                                binding.llEmptyList.visible()
                                binding.rvWisata.gone()
                            }
                        }
                        "alam" -> {
                            val dataAlam = wisata.data?.filter { a -> a.kategori == "Wisata Alam" }
                            if (dataAlam != null) {
                                if (dataAlam.isNotEmpty()) {
                                    binding.progressBar.gone()
                                    wisataAdapter.setWisata(dataAlam)
                                    wisataAdapter.notifyDataSetChanged()
                                } else {
                                    binding.llEmptyList.visible()
                                    binding.rvWisata.gone()
                                }
                            } else {
                                binding.llEmptyList.visible()
                                binding.rvWisata.gone()
                            }
                        }
                        "religi" -> {
                            val dataReligi =
                                wisata.data?.filter { a -> a.kategori == "Wisata Religi" }
                            if (dataReligi != null) {
                                if (dataReligi.isNotEmpty()) {
                                    binding.progressBar.gone()
                                    wisataAdapter.setWisata(dataReligi)
                                    wisataAdapter.notifyDataSetChanged()
                                } else {
                                    binding.llEmptyList.visible()
                                    binding.rvWisata.gone()
                                }
                            } else {
                                binding.llEmptyList.visible()
                                binding.rvWisata.gone()
                            }
                        }
                        "budaya" -> {
                            val dataBudaya =
                                wisata.data?.filter { a -> a.kategori == "Wisata Budaya" }
                            if (dataBudaya != null) {
                                if (dataBudaya.isNotEmpty()) {
                                    binding.progressBar.gone()
                                    wisataAdapter.setWisata(dataBudaya)
                                    wisataAdapter.notifyDataSetChanged()
                                } else {
                                    binding.llEmptyList.visible()
                                    binding.rvWisata.gone()
                                }
                            } else {
                                binding.llEmptyList.visible()
                                binding.rvWisata.gone()
                            }
                        }
                    }
                }

                Status.LOADING -> {
                    binding.progressBar.isVisible
                }

                Status.ERROR -> {
                    binding.progressBar.gone()
                    Toast.makeText(this, "Terjadi kesalahan ${wisata.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        with(binding.rvWisata) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = wisataAdapter
        }
    }

    override fun onItemClick(data: Wisata) {
        val moveWithObjectIntent = Intent(this, DetailWisataActivity::class.java)
        moveWithObjectIntent.putExtra(DetailWisataActivity.DATA, data)
        startActivity(moveWithObjectIntent)
    }
}