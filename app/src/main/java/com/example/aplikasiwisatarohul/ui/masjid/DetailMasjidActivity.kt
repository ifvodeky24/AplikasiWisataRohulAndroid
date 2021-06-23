package com.example.aplikasiwisatarohul.ui.masjid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Masjid
import com.example.aplikasiwisatarohul.databinding.ActivityDetailAtmBinding
import com.example.aplikasiwisatarohul.databinding.ActivityDetailMasjidBinding
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailMasjidActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailMasjidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Masjid"
        binding = ActivityDetailMasjidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val masjid = intent.getParcelableExtra<Masjid>(DATA) as Masjid

        Timber.d("masjid $masjid")

        binding.tvNamaMasjid.text = masjid.nama_masjid
        binding.tvAlamat.text = masjid.alamat
        binding.tvKelurahan.text = masjid.kelurahan
        binding.tvKecamatan.text = masjid.kecamatan
        binding.tvInformasi.text = masjid.informasi
        binding.tvTanggalUpdate.text = masjid.updatedAt
    }
}