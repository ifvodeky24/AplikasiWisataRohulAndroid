package com.example.aplikasiwisatarohul.ui.spbu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Spbu
import com.example.aplikasiwisatarohul.databinding.ActivityDetailAtmBinding
import com.example.aplikasiwisatarohul.databinding.ActivityDetailSpbuBinding
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailSpbuActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailSpbuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail SPBU"
        binding = ActivityDetailSpbuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spbu = intent.getParcelableExtra<Spbu>(DATA) as Spbu

        Timber.d("spbu $spbu")

        binding.tvNamaSpbu.text = spbu.nama_spbu
        binding.tvAlamat.text = spbu.alamat
        binding.tvKelurahan.text = spbu.kelurahan
        binding.tvKecamatan.text = spbu.kecamatan
        binding.tvInformasi.text = spbu.informasi
        binding.tvTanggalUpdate.text = spbu.updatedAt
    }
}