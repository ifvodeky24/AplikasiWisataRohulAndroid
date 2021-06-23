package com.example.aplikasiwisatarohul.ui.travel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasiwisatarohul.R
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.data.source.remote.response.Travel
import com.example.aplikasiwisatarohul.ui.atm.DetailAtmActivity
import timber.log.Timber

class DetailTravelActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Travel"
        setContentView(R.layout.activity_detail_travel)

        val travel = intent.getParcelableExtra<Travel>(DATA) as Travel

        Timber.d("travel $travel")
    }
}