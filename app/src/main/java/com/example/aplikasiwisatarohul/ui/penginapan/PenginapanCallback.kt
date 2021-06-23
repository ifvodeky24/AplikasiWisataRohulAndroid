package com.example.aplikasiwisatarohul.ui.penginapan

import com.example.aplikasiwisatarohul.data.source.remote.response.Penginapan

interface PenginapanCallback {
    fun onItemClick(data: Penginapan)
}