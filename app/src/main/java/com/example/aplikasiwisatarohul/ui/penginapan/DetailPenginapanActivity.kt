package com.example.aplikasiwisatarohul.ui.penginapan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Penginapan
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailPenginapanActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Penginapan"
        setContentView(R.layout.activity_detail_penginapan)

        val penginapan = intent.getParcelableExtra<Penginapan>(DATA) as Penginapan

        Timber.d("penginapan $penginapan")
    }
}