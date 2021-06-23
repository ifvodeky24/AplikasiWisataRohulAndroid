package com.example.aplikasiwisatarohul.ui.wisata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Wisata
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailWisataActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Wisata"
        setContentView(R.layout.activity_detail_wisata)

        val wisata = intent.getParcelableExtra<Wisata>(DATA) as Wisata

        Timber.d("wisata $wisata")
    }
}