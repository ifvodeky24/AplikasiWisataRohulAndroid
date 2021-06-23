package com.example.aplikasiwisatarohul.data.source.remote.service

import com.example.aplikasiwisatarohul.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("event/get-all")
    fun getAllEvent(
    ): Call<EventResponse>

    @GET("event/by-id")
    fun getEventById(
        @Query("id_event") id_event: String?,
    ): Call<EventResponse>

    @GET("atm/get-all")
    fun getAllAtm(
    ): Call<AtmResponse>

    @GET("atm/by-id")
    fun getAtmById(
        @Query("id_atm") id_atm: String?,
    ): Call<AtmResponse>

    @GET("masjid/get-all")
    fun getAllMasjid(
    ): Call<MasjidResponse>

    @GET("masjid/by-id")
    fun getMasjidById(
        @Query("id_masjid") id_masjid: String?,
    ): Call<MasjidResponse>

    @GET("spbu/get-all")
    fun getAllSpbu(
    ): Call<SpbuResponse>

    @GET("spbu/by-id")
    fun getSpbuById(
        @Query("id_spbu") id_spbu: String?,
    ): Call<SpbuResponse>

    @GET("wisata/get-all")
    fun getAllWisata(
    ): Call<WisataResponse>

    @GET("wisata/by-id")
    fun getWisataById(
        @Query("id_wisata") id_wisata: String?,
    ): Call<WisataResponse>

    @GET("berita/get-all")
    fun getAllBerita(
    ): Call<BeritaResponse>

    @GET("berita/by-id")
    fun getBeritaById(
        @Query("id_berita") id_berita: String?,
    ): Call<BeritaResponse>

    @GET("travel/get-all")
    fun getAllTravel(
    ): Call<TravelResponse>

    @GET("penginapan/get-all")
    fun getAllPenginapan(
    ): Call<PenginapanResponse>
}