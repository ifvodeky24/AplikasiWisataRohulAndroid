package com.example.aplikasiwisatarohul.ui.travel

import com.example.aplikasiwisatarohul.data.source.remote.response.Travel

interface TravelCallback {
    fun onItemClick(data: Travel)
}