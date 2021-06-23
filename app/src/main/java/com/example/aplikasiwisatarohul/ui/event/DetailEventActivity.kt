package com.example.aplikasiwisatarohul.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Event
import com.example.aplikasiwisatarohul.databinding.ActivityDetailAtmBinding
import com.example.aplikasiwisatarohul.databinding.ActivityDetailEventBinding
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailEventActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    private lateinit var binding: ActivityDetailEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Event"
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val event = intent.getParcelableExtra<Event>(DATA) as Event

        Timber.d("event $event")

        binding.tvNamaEvent.text = event.nama_event
        binding.tvAlamat.text = event.alamat
        binding.tvInformasi.text = event.informasi
        binding.tvTanggalPelaksanaan.text = event.createdAt
    }
}