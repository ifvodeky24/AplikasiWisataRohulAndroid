package com.example.aplikasiwisatarohul.data.source.remote.response

data class AtmResponse(
    val data: List<Atm>
)

data class Atm(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_atm: String,
    val informasi: String,
    val kecamatan: String,
    val kelurahan: String,
    val latitude: String,
    val longitude: String,
    val nama_atm: String,
    val updatedAt: String
)