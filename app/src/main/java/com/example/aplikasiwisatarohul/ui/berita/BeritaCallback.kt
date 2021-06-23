package com.example.aplikasiwisatarohul.ui.berita

import com.example.aplikasiwisatarohul.data.source.remote.response.Berita

interface BeritaCallback {
    fun onItemClick(data: Berita)
}