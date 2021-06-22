package com.example.aplikasiwisatarohul.data.source.remote.response

data class MasjidResponse(
    val data: List<Masjid>
)

data class Masjid(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_masjid: String,
    val informasi: String,
    val kecamatan: String,
    val kelurahan: String,
    val latitude: String,
    val longitude: String,
    val nama_masjid: String,
    val updatedAt: String
)