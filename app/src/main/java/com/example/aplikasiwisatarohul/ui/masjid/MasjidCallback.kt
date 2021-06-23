package com.example.aplikasiwisatarohul.ui.masjid

import com.example.aplikasiwisatarohul.data.source.remote.response.Masjid

interface MasjidCallback {
    fun onItemClick(data: Masjid)
}