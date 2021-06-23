package com.example.aplikasiwisatarohul.ui.berita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Berita
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ActivityDetailBeritaBinding
import com.example.aplikasiwisatarohul.databinding.ActivityHomeBinding
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailBeritaActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailBeritaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Berita"
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val berita = intent.getParcelableExtra<Berita>(DATA) as Berita

        Timber.d("berita $berita")

        binding.tvJudulBerita.text = berita.judul_berita
        binding.tvNamaPengarang.text = berita.nama_pengarang
        binding.tvInformasi.text = berita.informasi
        binding.tvTanggalRilis.text = berita.createdAt

        Glide.with(this)
            .load(ApiConfig.berita_images + berita.foto)
            .into(binding.ivBerita)
    }
}