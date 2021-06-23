package com.example.aplikasiwisatarohul.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AtmResponse(
    val data: List<Atm>
)

@Parcelize
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
) : Parcelable