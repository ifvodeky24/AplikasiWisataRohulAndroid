package com.example.aplikasiwisatarohul.ui.penginapan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Penginapan
import com.example.aplikasiwisatarohul.vo.Resource

class PenginapanViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllPenginapan(): LiveData<Resource<List<Penginapan>>> =
        appRepository.getAllPenginapan()
}