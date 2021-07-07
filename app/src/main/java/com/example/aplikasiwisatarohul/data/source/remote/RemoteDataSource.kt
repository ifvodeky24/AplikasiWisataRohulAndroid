package com.example.aplikasiwisatarohul.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplikasiwisatarohul.data.source.remote.response.*
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.utils.EMPTY_DATA
import com.example.aplikasiwisatarohul.utils.ERROR_CONNECTION
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource(private val apiConfig: ApiConfig) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiConfig)
            }
    }

    fun getAllEvent(): LiveData<ApiResponse<List<Event>>> {
        val listEvent = MutableLiveData<ApiResponse<List<Event>>>()

        apiConfig.client().getAllEvent().enqueue(object : Callback<EventResponse> {
            override fun onResponse(
                call: Call<EventResponse>,
                response: Response<EventResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listEvent.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listEvent.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listEvent.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                listEvent.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listEvent
    }

    fun getAllAtm(): LiveData<ApiResponse<List<Atm>>> {
        val listAtm = MutableLiveData<ApiResponse<List<Atm>>>()

        apiConfig.client().getAllAtm().enqueue(object : Callback<AtmResponse> {
            override fun onResponse(
                call: Call<AtmResponse>,
                response: Response<AtmResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listAtm.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listAtm.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listAtm.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<AtmResponse>, t: Throwable) {
                listAtm.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listAtm
    }

    fun getAllBerita(): LiveData<ApiResponse<List<Berita>>> {
        val listBerita = MutableLiveData<ApiResponse<List<Berita>>>()

        apiConfig.client().getAllBerita().enqueue(object : Callback<BeritaResponse> {
            override fun onResponse(
                call: Call<BeritaResponse>,
                response: Response<BeritaResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listBerita.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listBerita.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listBerita.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                listBerita.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listBerita
    }

    fun getAllMasjid(): LiveData<ApiResponse<List<Masjid>>> {
        val listMasjid = MutableLiveData<ApiResponse<List<Masjid>>>()

        apiConfig.client().getAllMasjid().enqueue(object : Callback<MasjidResponse> {
            override fun onResponse(
                call: Call<MasjidResponse>,
                response: Response<MasjidResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listMasjid.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listMasjid.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listMasjid.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<MasjidResponse>, t: Throwable) {
                listMasjid.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listMasjid
    }

    fun getAllSpbu(): LiveData<ApiResponse<List<Spbu>>> {
        val listSpbu = MutableLiveData<ApiResponse<List<Spbu>>>()

        apiConfig.client().getAllSpbu().enqueue(object : Callback<SpbuResponse> {
            override fun onResponse(
                call: Call<SpbuResponse>,
                response: Response<SpbuResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listSpbu.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listSpbu.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listSpbu.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<SpbuResponse>, t: Throwable) {
                listSpbu.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listSpbu
    }

    fun getAllWisata(): LiveData<ApiResponse<List<Wisata>>> {
        val listWisata = MutableLiveData<ApiResponse<List<Wisata>>>()

        apiConfig.client().getAllWisata().enqueue(object : Callback<WisataResponse> {
            override fun onResponse(
                call: Call<WisataResponse>,
                response: Response<WisataResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listWisata.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listWisata.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listWisata.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<WisataResponse>, t: Throwable) {
                listWisata.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listWisata
    }

    fun getAllTravel(): LiveData<ApiResponse<List<Travel>>> {
        val listTravel = MutableLiveData<ApiResponse<List<Travel>>>()

        apiConfig.client().getAllTravel().enqueue(object : Callback<TravelResponse> {
            override fun onResponse(
                call: Call<TravelResponse>,
                response: Response<TravelResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listTravel.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listTravel.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listTravel.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<TravelResponse>, t: Throwable) {
                listTravel.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listTravel
    }

    fun getAllPenginapan(): LiveData<ApiResponse<List<Penginapan>>> {
        val listPenginapan = MutableLiveData<ApiResponse<List<Penginapan>>>()

        apiConfig.client().getAllPenginapan().enqueue(object : Callback<PenginapanResponse> {
            override fun onResponse(
                call: Call<PenginapanResponse>,
                response: Response<PenginapanResponse>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.let {
                        if (it.isNotEmpty()) {
                            Timber.d("oiii ${response.body()?.data}")
                            listPenginapan.value = ApiResponse.success(it)
                        } else if (it.isEmpty()) {
                            listPenginapan.value = ApiResponse.empty(EMPTY_DATA, it)
                        }
                    }
                } else {
                    response.body()?.data?.let {
                        listPenginapan.value = ApiResponse.error(ERROR_CONNECTION, it)
                    }
                }
            }

            override fun onFailure(call: Call<PenginapanResponse>, t: Throwable) {
                listPenginapan.value = ApiResponse.error(ERROR_CONNECTION, null)
            }
        })
        return listPenginapan
    }

    fun getNearbyWisata(lat: String, long: String): LiveData<ApiResponse<List<Wisata>>> {
        val listWisata = MutableLiveData<ApiResponse<List<Wisata>>>()

        apiConfig.client().getNearbyWisata(lat, long)
            .enqueue(object : Callback<WisataResponse> {
                override fun onResponse(
                    call: Call<WisataResponse>,
                    response: Response<WisataResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listWisata.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listWisata.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listWisata.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<WisataResponse>, t: Throwable) {
                    listWisata.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listWisata
    }

    fun getNearbyTravel(lat: String, long: String): LiveData<ApiResponse<List<Travel>>> {
        val listTravel = MutableLiveData<ApiResponse<List<Travel>>>()

        apiConfig.client().getNearbyTravel(lat, long)
            .enqueue(object : Callback<TravelResponse> {
                override fun onResponse(
                    call: Call<TravelResponse>,
                    response: Response<TravelResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listTravel.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listTravel.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listTravel.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<TravelResponse>, t: Throwable) {
                    listTravel.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listTravel
    }

    fun getNearbySpbu(lat: String, long: String): LiveData<ApiResponse<List<Spbu>>> {
        val listSpbu = MutableLiveData<ApiResponse<List<Spbu>>>()

        apiConfig.client().getNearbySpbu(lat, long)
            .enqueue(object : Callback<SpbuResponse> {
                override fun onResponse(
                    call: Call<SpbuResponse>,
                    response: Response<SpbuResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listSpbu.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listSpbu.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listSpbu.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<SpbuResponse>, t: Throwable) {
                    listSpbu.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listSpbu
    }

    fun getNearbyPenginapan(
        lat: String,
        long: String
    ): LiveData<ApiResponse<List<Penginapan>>> {
        val listPenginapan = MutableLiveData<ApiResponse<List<Penginapan>>>()

        apiConfig.client().getNearbyPenginapan(lat, long)
            .enqueue(object : Callback<PenginapanResponse> {
                override fun onResponse(
                    call: Call<PenginapanResponse>,
                    response: Response<PenginapanResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listPenginapan.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listPenginapan.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listPenginapan.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<PenginapanResponse>, t: Throwable) {
                    listPenginapan.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listPenginapan
    }

    fun getNearbyMasjid(
        lat: String,
        long: String
    ): LiveData<ApiResponse<List<Masjid>>> {
        val listMasjid = MutableLiveData<ApiResponse<List<Masjid>>>()

        apiConfig.client().getNearbyMasjid(lat, long)
            .enqueue(object : Callback<MasjidResponse> {
                override fun onResponse(
                    call: Call<MasjidResponse>,
                    response: Response<MasjidResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listMasjid.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listMasjid.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listMasjid.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<MasjidResponse>, t: Throwable) {
                    listMasjid.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listMasjid
    }

    fun getNearbyEvent(
        lat: String,
        long: String
    ): LiveData<ApiResponse<List<Event>>> {
        val listEvent = MutableLiveData<ApiResponse<List<Event>>>()

        apiConfig.client().getNearbyEvent(lat, long)
            .enqueue(object : Callback<EventResponse> {
                override fun onResponse(
                    call: Call<EventResponse>,
                    response: Response<EventResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listEvent.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listEvent.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listEvent.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                    listEvent.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listEvent
    }

    fun getNearbyAtm(
        lat: String,
        long: String
    ): LiveData<ApiResponse<List<Atm>>> {
        val listAtm = MutableLiveData<ApiResponse<List<Atm>>>()

        apiConfig.client().getNearbyAtm(lat, long)
            .enqueue(object : Callback<AtmResponse> {
                override fun onResponse(
                    call: Call<AtmResponse>,
                    response: Response<AtmResponse>
                ) {
                    if (response.code() == 200) {
                        response.body()?.data?.let {
                            if (it.isNotEmpty()) {
                                Timber.d("oiii ${response.body()?.data}")
                                listAtm.value = ApiResponse.success(it)
                            } else if (it.isEmpty()) {
                                listAtm.value = ApiResponse.empty(EMPTY_DATA, it)
                            }
                        }
                    } else {
                        response.body()?.data?.let {
                            listAtm.value = ApiResponse.error(ERROR_CONNECTION, it)
                        }
                    }
                }

                override fun onFailure(call: Call<AtmResponse>, t: Throwable) {
                    listAtm.value = ApiResponse.error(ERROR_CONNECTION, null)
                }
            })
        return listAtm
    }
}