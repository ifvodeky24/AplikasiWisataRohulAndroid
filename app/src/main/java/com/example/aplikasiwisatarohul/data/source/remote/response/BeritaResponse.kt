package com.example.aplikasiwisatarohul.data.source.remote.response

data class BeritaResponse(
    val data: List<Berita>
)

data class Berita(
    val createdAt: String,
    val foto: String,
    val id_berita: String,
    val informasi: String,
    val judul_berita: String,
    val nama_pengarang: String,
    val updatedAt: String
)