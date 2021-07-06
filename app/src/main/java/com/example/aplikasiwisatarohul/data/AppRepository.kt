package com.example.aplikasiwisatarohul.data

import androidx.lifecycle.LiveData
import com.example.aplikasiwisatarohul.data.source.remote.ApiResponse
import com.example.aplikasiwisatarohul.data.source.remote.RemoteDataSource
import com.example.aplikasiwisatarohul.data.source.remote.response.*
import com.example.aplikasiwisatarohul.utils.AppExecutors
import com.example.aplikasiwisatarohul.vo.Resource

class AppRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            appExecutors: AppExecutors
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData, appExecutors)
            }
    }

    override fun getAllEvent(): LiveData<Resource<List<Event>>> {
        return object : NetworkOnlyResource<List<Event>, List<Event>>(appExecutors) {
            override fun handleCallResult(item: List<Event>?): List<Event>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Event>>> {
                return remoteDataSource.getAllEvent()
            }

        }.asLiveData()
    }

    override fun getAllAtm(): LiveData<Resource<List<Atm>>> {
        return object : NetworkOnlyResource<List<Atm>, List<Atm>>(appExecutors) {
            override fun handleCallResult(item: List<Atm>?): List<Atm>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Atm>>> {
                return remoteDataSource.getAllAtm()
            }

        }.asLiveData()
    }

    override fun getAllBerita(): LiveData<Resource<List<Berita>>> {
        return object : NetworkOnlyResource<List<Berita>, List<Berita>>(appExecutors) {
            override fun handleCallResult(item: List<Berita>?): List<Berita>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Berita>>> {
                return remoteDataSource.getAllBerita()
            }

        }.asLiveData()
    }

    override fun getAllMasjid(): LiveData<Resource<List<Masjid>>> {
        return object : NetworkOnlyResource<List<Masjid>, List<Masjid>>(appExecutors) {
            override fun handleCallResult(item: List<Masjid>?): List<Masjid>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Masjid>>> {
                return remoteDataSource.getAllMasjid()
            }

        }.asLiveData()
    }

    override fun getAllSpbu(): LiveData<Resource<List<Spbu>>> {
        return object : NetworkOnlyResource<List<Spbu>, List<Spbu>>(appExecutors) {
            override fun handleCallResult(item: List<Spbu>?): List<Spbu>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Spbu>>> {
                return remoteDataSource.getAllSpbu()
            }

        }.asLiveData()
    }

//    override fun getAllTravel(): LiveData<Resource<List<Travel>>> {
//        return object : NetworkOnlyResource<List<Travel>, List<Travel>>(appExecutors) {
//            override fun handleCallResult(item: List<Travel>?): List<Travel>? {
//                return item
//            }
//
//            override fun createCall(): LiveData<ApiResponse<List<Travel>>> {
//                return remoteDataSource.getAllTravel()
//            }
//
//        }.asLiveData()
//    }

    override fun getAllWisata(): LiveData<Resource<List<Wisata>>> {
        return object : NetworkOnlyResource<List<Wisata>, List<Wisata>>(appExecutors) {
            override fun handleCallResult(item: List<Wisata>?): List<Wisata>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Wisata>>> {
                return remoteDataSource.getAllWisata()
            }

        }.asLiveData()
    }

    override fun getAllTravel(): LiveData<Resource<List<Travel>>> {
        return object : NetworkOnlyResource<List<Travel>, List<Travel>>(appExecutors) {
            override fun handleCallResult(item: List<Travel>?): List<Travel>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Travel>>> {
                return remoteDataSource.getAllTravel()
            }

        }.asLiveData()
    }

    override fun getAllPenginapan(): LiveData<Resource<List<Penginapan>>> {
        return object : NetworkOnlyResource<List<Penginapan>, List<Penginapan>>(appExecutors) {
            override fun handleCallResult(item: List<Penginapan>?): List<Penginapan>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<Penginapan>>> {
                return remoteDataSource.getAllPenginapan()
            }

        }.asLiveData()
    }

    override fun getNearbyWisata(
        lat: String,
        long: String
    ): LiveData<Resource<List<WisataNearby>>> {
        return object : NetworkOnlyResource<List<WisataNearby>, List<WisataNearby>>(appExecutors) {
            override fun handleCallResult(item: List<WisataNearby>?): List<WisataNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<WisataNearby>>> {
                return remoteDataSource.getNearbyWisata(lat, long)
            }

        }.asLiveData()
    }

    override fun getNearbyTravel(
        lat: String,
        long: String
    ): LiveData<Resource<List<TravelNearby>>> {
        return object : NetworkOnlyResource<List<TravelNearby>, List<TravelNearby>>(appExecutors) {
            override fun handleCallResult(item: List<TravelNearby>?): List<TravelNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<TravelNearby>>> {
                return remoteDataSource.getNearbyTravel(lat, long)
            }

        }.asLiveData()
    }

    override fun getNearbySpbu(lat: String, long: String): LiveData<Resource<List<SpbuNearby>>> {
        return object : NetworkOnlyResource<List<SpbuNearby>, List<SpbuNearby>>(appExecutors) {
            override fun handleCallResult(item: List<SpbuNearby>?): List<SpbuNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<SpbuNearby>>> {
                return remoteDataSource.getNearbySpbu(lat, long)
            }

        }.asLiveData()
    }

    override fun getNearbyPenginapan(
        lat: String,
        long: String
    ): LiveData<Resource<List<PenginapanNearby>>> {
        return object :
            NetworkOnlyResource<List<PenginapanNearby>, List<PenginapanNearby>>(appExecutors) {
            override fun handleCallResult(item: List<PenginapanNearby>?): List<PenginapanNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<PenginapanNearby>>> {
                return remoteDataSource.getNearbyPenginapan(lat, long)
            }

        }.asLiveData()
    }

    override fun getNearbyMasjid(
        lat: String,
        long: String
    ): LiveData<Resource<List<MasjidNearby>>> {
        return object : NetworkOnlyResource<List<MasjidNearby>, List<MasjidNearby>>(appExecutors) {
            override fun handleCallResult(item: List<MasjidNearby>?): List<MasjidNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<MasjidNearby>>> {
                return remoteDataSource.getNearbyMasjid(lat, long)
            }

        }.asLiveData()
    }

    override fun getNearbyEvent(lat: String, long: String): LiveData<Resource<List<EventNearby>>> {
        return object : NetworkOnlyResource<List<EventNearby>, List<EventNearby>>(appExecutors) {
            override fun handleCallResult(item: List<EventNearby>?): List<EventNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<EventNearby>>> {
                return remoteDataSource.getNearbyEvent(lat, long)
            }

        }.asLiveData()
    }

    override fun getNearbyAtm(lat: String, long: String): LiveData<Resource<List<AtmNearby>>> {
        return object : NetworkOnlyResource<List<AtmNearby>, List<AtmNearby>>(appExecutors) {
            override fun handleCallResult(item: List<AtmNearby>?): List<AtmNearby>? {
                return item
            }

            override fun createCall(): LiveData<ApiResponse<List<AtmNearby>>> {
                return remoteDataSource.getNearbyAtm(lat, long)
            }

        }.asLiveData()
    }
}