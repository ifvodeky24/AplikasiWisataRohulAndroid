package com.example.aplikasiwisatarohul.ui.masjid

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Masjid
import com.example.aplikasiwisatarohul.vo.Resource

class MasjidViewModel (private val appRepository: AppRepository) : ViewModel() {

    fun getAllMasjid(): LiveData<Resource<List<Masjid>>> =
        appRepository.getAllMasjid()

    fun getNearbyMasjid(
        lat: String,
        long: String
    ): LiveData<Resource<List<Masjid>>> =
        appRepository.getNearbyMasjid(lat, long)
}