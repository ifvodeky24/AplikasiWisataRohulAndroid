package com.example.aplikasiwisatarohul.ui.event

import com.example.aplikasiwisatarohul.data.source.remote.response.Event

interface EventCallback {
    fun onItemClick(data: Event)
}