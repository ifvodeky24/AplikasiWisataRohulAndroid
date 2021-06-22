package com.example.aplikasiwisatarohul.data.source.remote.response

data class TravelResponse(
    val data: List<Travel>
)

data class Travel(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_travel: String,
    val informasi: String,
    val latitude: String,
    val longitude: String,
    val nama_travel: String,
    val pemilik: String,
    val updatedAt: String
)