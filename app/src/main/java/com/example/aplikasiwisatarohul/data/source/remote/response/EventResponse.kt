package com.example.aplikasiwisatarohul.data.source.remote.response

data class EventResponse(
    val data: List<Event>
)

data class Event(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_event: String,
    val informasi: String,
    val latitude: String,
    val longitude: String,
    val nama_event: String,
    val updatedAt: String
)