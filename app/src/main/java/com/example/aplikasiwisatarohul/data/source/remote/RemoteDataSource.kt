package com.example.aplikasiwisatarohul.data.source.remote

import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig

class RemoteDataSource(private val apiConfig: ApiConfig) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiConfig)
            }
    }

//    fun getAllPerusahaan(): LiveData<ApiResponse<List<Perusahaan>>> {
//        val listPerusahaan = MutableLiveData<ApiResponse<List<Perusahaan>>>()
//
//        apiConfig.client().getAllPerusahaan().enqueue(object : Callback<PerusahaanResponse> {
//            override fun onResponse(
//                call: Call<PerusahaanResponse>,
//                response: Response<PerusahaanResponse>
//            ) {
//                if (response.code() == 200) {
//                    response.body()?.perusahaan?.let {
//                        if (it.isNotEmpty()) {
//                            Timber.d("oiii ${response.body()?.perusahaan}")
//                            listPerusahaan.value = ApiResponse.success(it)
//                        } else if (it.isEmpty()) {
//                            listPerusahaan.value = ApiResponse.empty(EMPTY_DATA, it)
//                        }
//                    }
//
//                } else {
//                    response.body()?.perusahaan?.let {
//                        listPerusahaan.value = ApiResponse.error(ERROR_CONNECTION, it)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<PerusahaanResponse>, t: Throwable) {
//                listPerusahaan.value = ApiResponse.error(ERROR_CONNECTION, null)
//            }
//        })
//
//        return listPerusahaan
//    }


}