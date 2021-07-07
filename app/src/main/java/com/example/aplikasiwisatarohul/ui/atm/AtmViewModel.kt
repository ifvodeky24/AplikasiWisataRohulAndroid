package com.example.aplikasiwisatarohul.ui.atm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Atm
import com.example.aplikasiwisatarohul.vo.Resource

class AtmViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllAtm(): LiveData<Resource<List<Atm>>> =
        appRepository.getAllAtm()

    fun getNearbyAtm(
        lat: String,
        long: String
    ): LiveData<Resource<List<Atm>>> =
        appRepository.getNearbyAtm(lat, long)
}