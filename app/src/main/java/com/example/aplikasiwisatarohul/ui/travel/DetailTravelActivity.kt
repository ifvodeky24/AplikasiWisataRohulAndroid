package com.example.aplikasiwisatarohul.ui.travel

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.aplikasiwisatarohul.data.source.remote.response.Travel
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.databinding.ActivityDetailTravelBinding
import com.example.aplikasiwisatarohul.ui.spbu.DetailSpbuActivity
import timber.log.Timber

class DetailTravelActivity : AppCompatActivity() {

    companion object {
        const val DATA = "data"
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    private lateinit var binding: ActivityDetailTravelBinding
    private var locationManager: LocationManager?= null
    lateinit var latitude: String
    lateinit var longitude: String
    private var latitude_know: Double? = null
    private var longitude_know: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detail Travel"
        binding = ActivityDetailTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        val travel = intent.getParcelableExtra<Travel>(DATA) as Travel

        Timber.d("travel $travel")

        binding.tvNamaTravel.text = travel.nama_travel
        binding.tvAlamat.text = travel.alamat
        binding.tvKelurahan.text = travel.kelurahan
        binding.tvKecamatan.text = travel.kecamatan
        binding.tvInformasi.text = travel.informasi
        binding.tvTanggalUpdate.text = travel.updatedAt

        Glide.with(this)
            .load(ApiConfig.travel_images + travel.foto)
            .into(binding.ivTravel)

        latitude = travel.latitude
        longitude = travel.longitude

        getLocation()
    }

    private fun getLocation() {
        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                latitude_know = location.latitude
                longitude_know = location.longitude
                Timber.e(latitude_know.toString())
                Timber.e(longitude_know.toString())
                Timber.e(latitude)
                Timber.e(longitude)
                binding.fabMapsDestinasi.setOnClickListener {
                    val mapIntent = Uri.parse(
                        "http://maps.google.com/maps?saddr=$latitude_know,$longitude_know&daddr=$latitude,$longitude"
                    ).let { location ->
                        Intent(Intent.ACTION_VIEW, location);
                    }
                    startActivity(mapIntent)
                }

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }

        }

        try {
            locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
        } catch (ex:SecurityException) {
            Toast.makeText(applicationContext, "Lokasi tidak ditemukan!", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION
            )
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]){
                PackageManager.PERMISSION_GRANTED -> getLocation()
                PackageManager.PERMISSION_DENIED -> "Denied" //Tell to user the need of grant permission
            }
        }
    }
}