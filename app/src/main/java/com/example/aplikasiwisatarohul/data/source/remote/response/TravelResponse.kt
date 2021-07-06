package com.example.aplikasiwisatarohul.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class TravelResponse(
    val data: List<Travel>
)

@Parcelize
data class Travel(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_travel: String,
    val informasi: String,
    val latitude: String,
    val longitude: String,
    val nama_travel: String,
    val kecamatan: String,
    val kelurahan: String,
    val updatedAt: String
) : Parcelable

data class TravelNearbyResponse(
    val data: List<TravelNearby>
)

@Parcelize
data class TravelNearby(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_travel: String,
    val informasi: String,
    val latitude: String,
    val longitude: String,
    val nama_travel: String,
    val kecamatan: String,
    val kelurahan: String,
    val updatedAt: String,
    val distance: String
) : Parcelable