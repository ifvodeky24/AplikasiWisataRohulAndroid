package com.example.aplikasiwisatarohul.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.response.Event
import com.example.aplikasiwisatarohul.vo.Resource

class EventViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getAllEvent(): LiveData<Resource<List<Event>>> =
        appRepository.getAllEvent()
}