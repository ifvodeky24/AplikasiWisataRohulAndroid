package com.example.aplikasiwisatarohul.ui.atm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.databinding.ActivityDetailAtmBinding
import com.example.aplikasiwisatarohul.databinding.ActivityDetailBeritaBinding
import timber.log.Timber

class DetailAtmActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailAtmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail ATM"
        binding = ActivityDetailAtmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val atm = intent.getParcelableExtra<Atm>(DATA) as Atm

        Timber.d("atm $atm")

        binding.tvNamaAtm.text = atm.nama_atm
        binding.tvAlamat.text = atm.alamat
        binding.tvKelurahan.text = atm.kelurahan
        binding.tvKecamatan.text = atm.kecamatan
        binding.tvInformasi.text = atm.informasi
        binding.tvTanggalUpdate.text = atm.updatedAt
    }
}