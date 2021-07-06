package com.example.aplikasiwisatarohul.ui.travel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Travel
import com.example.aplikasiwisatarohul.data.source.remote.response.TravelNearby
import com.example.aplikasiwisatarohul.data.source.remote.response.WisataNearby
import com.example.aplikasiwisatarohul.vo.Resource

class TravelViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllTravel(): LiveData<Resource<List<Travel>>> =
        appRepository.getAllTravel()

    fun getNearbyTravel(
        lat: String,
        long: String
    ): LiveData<Resource<List<TravelNearby>>> =
        appRepository.getNearbyTravel(lat, long)
}