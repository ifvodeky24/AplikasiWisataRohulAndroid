package com.example.aplikasiwisatarohul.ui.wisata

import com.example.aplikasiwisatarohul.data.source.remote.response.Wisata

interface WisataCallback {
    fun onItemClick(data: Wisata)
}