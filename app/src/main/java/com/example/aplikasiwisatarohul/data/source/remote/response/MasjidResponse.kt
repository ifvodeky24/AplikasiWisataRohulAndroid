package com.example.aplikasiwisatarohul.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MasjidResponse(
    val data: List<Masjid>
)

@Parcelize
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
) : Parcelable

data class MasjidNearbyResponse(
    val data: List<MasjidNearby>
)

@Parcelize
data class MasjidNearby(
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
    val updatedAt: String,
    val distance: String
) : Parcelable