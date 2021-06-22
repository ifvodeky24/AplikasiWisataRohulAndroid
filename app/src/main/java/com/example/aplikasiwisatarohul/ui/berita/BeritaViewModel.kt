package com.example.aplikasiwisatarohul.ui.berita

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Berita
import com.example.aplikasiwisatarohul.vo.Resource

class BeritaViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllBerita(): LiveData<Resource<List<Berita>>> =
        appRepository.getAllBerita()
}