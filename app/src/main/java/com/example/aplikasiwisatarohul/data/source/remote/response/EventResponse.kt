package com.example.aplikasiwisatarohul.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class EventResponse(
    val data: List<Event>
)

@Parcelize
data class Event(
    val alamat: String,
    val createdAt: String,
    val foto: String,
    val id_event: String,
    val informasi: String,
    val latitude: String,
    val longitude: String,
    val nama_event: String,
    val updatedAt: String,
    val distance: String?
) : Parcelable

//data class EventNearbyResponse(
//    val data: List<EventNearby>
//)
//
//@Parcelize
//data class EventNearby(
//    val alamat: String,
//    val createdAt: String,
//    val foto: String,
//    val id_event: String,
//    val informasi: String,
//    val latitude: String,
//    val longitude: String,
//    val nama_event: String,
//    val updatedAt: String,
//    val distance: String
//) : Parcelable