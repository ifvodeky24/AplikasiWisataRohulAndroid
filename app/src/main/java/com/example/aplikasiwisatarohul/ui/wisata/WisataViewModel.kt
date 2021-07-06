package com.example.aplikasiwisatarohul.ui.wisata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Wisata
import com.example.aplikasiwisatarohul.data.source.remote.response.WisataNearby
import com.example.aplikasiwisatarohul.vo.Resource

class WisataViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllWisata(): LiveData<Resource<List<Wisata>>> =
        appRepository.getAllWisata()

    fun getNearbyWisata(
        lat: String,
        long: String
    ): LiveData<Resource<List<WisataNearby>>> =
        appRepository.getNearbyWisata(lat, long)
}