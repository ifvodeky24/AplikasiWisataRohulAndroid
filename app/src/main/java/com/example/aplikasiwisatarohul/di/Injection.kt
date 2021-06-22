package com.example.aplikasiwisatarohul.di

import android.content.Context
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.data.source.remote.RemoteDataSource
import com.example.aplikasiwisatarohul.data.source.remote.service.ApiConfig
import com.example.aplikasiwisatarohul.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): AppRepository {

        val remoteRepository = RemoteDataSource.getInstance(ApiConfig())
        val appExecutors = AppExecutors()

        return AppRepository.getInstance(remoteRepository, appExecutors)
    }
}