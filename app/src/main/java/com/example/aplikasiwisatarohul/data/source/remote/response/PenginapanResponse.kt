package com.example.aplikasiwisatarohul.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PenginapanResponse(
    val data: List<Penginapan>
)

@Parcelize
data class Penginapan(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_penginapan: String,
    val informasi: String,
    val latitude: String,
    val longitude: String,
    val nama_penginapan: String,
    val pemilik: String,
    val updatedAt: String
) : Parcelable