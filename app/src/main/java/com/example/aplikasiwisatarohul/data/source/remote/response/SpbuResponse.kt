package com.example.aplikasiwisatarohul.data.source.remote.response

data class SpbuResponse(
    val data: List<Spbu>
)

data class Spbu(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_spbu: String,
    val informasi: String,
    val kecamatan: String,
    val kelurahan: String,
    val latitude: String,
    val longitude: String,
    val nama_spbu: String,
    val updatedAt: String
)