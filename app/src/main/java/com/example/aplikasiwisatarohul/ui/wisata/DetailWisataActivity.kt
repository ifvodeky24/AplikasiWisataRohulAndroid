package com.example.aplikasiwisatarohul.ui.wisata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Wisata
import com.example.aplikasiwisatarohul.databinding.ActivityDetailAtmBinding
import com.example.aplikasiwisatarohul.databinding.ActivityDetailWisataBinding
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailWisataActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailWisataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Wisata"
        binding = ActivityDetailWisataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wisata = intent.getParcelableExtra<Wisata>(DATA) as Wisata

        Timber.d("wisata $wisata")

        binding.tvNamaWisata.text = wisata.nama_wisata
        binding.tvAlamat.text = wisata.alamat
        binding.tvKelurahan.text = wisata.kelurahan
        binding.tvKecamatan.text = wisata.kecamatan
        binding.tvInformasi.text = wisata.informasi
        binding.tvTanggalUpdate.text = wisata.updatedAt
    }
}