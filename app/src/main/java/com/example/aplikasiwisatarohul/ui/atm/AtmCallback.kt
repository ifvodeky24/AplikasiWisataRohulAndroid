package com.example.aplikasiwisatarohul.ui.atm

import com.example.aplikasiwisatarohul.data.source.remote.response.Atm

interface AtmCallback {
    fun onItemClick(data : Atm)
}