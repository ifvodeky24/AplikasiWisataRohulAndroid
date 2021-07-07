package com.example.aplikasiwisatarohul.data

import androidx.lifecycle.LiveData
import com.example.aplikasiwisatarohul.data.source.remote.response.*
import com.example.aplikasiwisatarohul.vo.Resource

interface AppDataSource {

    fun getAllEvent(): LiveData<Resource<List<Event>>>

    fun getAllAtm(): LiveData<Resource<List<Atm>>>

    fun getAllBerita(): LiveData<Resource<List<Berita>>>

    fun getAllMasjid(): LiveData<Resource<List<Masjid>>>

    fun getAllSpbu(): LiveData<Resource<List<Spbu>>>

    fun getAllWisata(): LiveData<Resource<List<Wisata>>>

    fun getAllTravel(): LiveData<Resource<List<Travel>>>

    fun getAllPenginapan(): LiveData<Resource<List<Penginapan>>>

    fun getNearbyWisata(lat: String, long: String): LiveData<Resource<List<Wisata>>>

    fun getNearbyTravel(lat: String, long: String): LiveData<Resource<List<Travel>>>

    fun getNearbySpbu(lat: String, long: String): LiveData<Resource<List<Spbu>>>

    fun getNearbyPenginapan(lat: String, long: String): LiveData<Resource<List<Penginapan>>>

    fun getNearbyMasjid(lat: String, long: String): LiveData<Resource<List<Masjid>>>

    fun getNearbyEvent(lat: String, long: String): LiveData<Resource<List<Event>>>

    fun getNearbyAtm(lat: String, long: String): LiveData<Resource<List<Atm>>>
}