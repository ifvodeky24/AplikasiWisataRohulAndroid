package com.example.aplikasiwisatarohul.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class WisataResponse(
    val data: List<Wisata>
)

@Parcelize
data class Wisata(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_wisata: String,
    val kategori: String,
    val kecamatan: String,
    val kelurahan: String,
    val latitude: String,
    val longitude: String,
    val nama_wisata: String,
    val updatedAt: String
) : Parcelable
