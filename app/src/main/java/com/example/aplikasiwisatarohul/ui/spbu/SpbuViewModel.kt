package com.example.aplikasiwisatarohul.ui.spbu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Spbu
import com.example.aplikasiwisatarohul.vo.Resource

class SpbuViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllSpbu(): LiveData<Resource<List<Spbu>>> =
        appRepository.getAllSpbu()

    fun getNearbySpbu(
        lat: String,
        long: String
    ): LiveData<Resource<List<Spbu>>> =
        appRepository.getNearbySpbu(lat, long)
}