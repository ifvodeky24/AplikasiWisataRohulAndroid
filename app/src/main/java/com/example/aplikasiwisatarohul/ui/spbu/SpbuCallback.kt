package com.example.aplikasiwisatarohul.ui.spbu

import com.example.aplikasiwisatarohul.data.source.remote.response.Spbu

interface SpbuCallback {
    fun onItemClick(data: Spbu)
}