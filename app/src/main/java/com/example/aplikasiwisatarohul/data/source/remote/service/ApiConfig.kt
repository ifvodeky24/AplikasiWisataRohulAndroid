package com.example.aplikasiwisatarohul.data.source.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        var event_images =
            "http://arslyn.com/wisata-rohul/web/files/images/event_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/event_images/"

        var atm_images=
            "http://arslyn.com/wisata-rohul/web/files/images/atm_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/atm_images/"

        var berita_images =
            "http://arslyn.com/wisata-rohul/web/files/images/berita_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/berita_images/"

        var masjid_images =
            "http://arslyn.com/wisata-rohul/web/files/images/masjid_images/"
//            "http://1192.168.0.115/wisata-rohul/web/files/images/masjid_images/"

        var penginapan_images =
            "http://arslyn.com/wisata-rohul/web/files/images/penginapan_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/penginapan_images/"

        var spbu_images =
            "http://arslyn.com/wisata-rohul/web/files/images/spbu_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/spbu_images/"

        var wisata_images =
            "http://arslyn.com/wisata-rohul/web/files/images/wisata_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/wisata_images/"

        var travel_images =
            "http://arslyn.com/wisata-rohul/web/files/images/travel_images/"
//            "http://192.168.0.115/wisata-rohul/web/files/images/travel_images/"
    }

    private var retrofit: Retrofit? = null

    private fun getApiService(): Retrofit {
        if (retrofit == null) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl("http://arslyn.com/wisata-rohul/api/v1/")
//                .baseUrl("http://192.168.0.115/wisata-rohul/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit as Retrofit
        }
        return retrofit as Retrofit
    }

    fun client(): ApiService = getApiService().create(ApiService::class.java)
}