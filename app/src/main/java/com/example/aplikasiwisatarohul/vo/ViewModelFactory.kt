package com.example.aplikasiwisatarohul.vo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasiwisatarohul.data.AppRepository
import com.example.aplikasiwisatarohul.di.Injection
import com.example.aplikasiwisatarohul.ui.atm.AtmViewModel
import com.example.aplikasiwisatarohul.ui.berita.BeritaViewModel
import com.example.aplikasiwisatarohul.ui.event.EventViewModel
import com.example.aplikasiwisatarohul.ui.masjid.MasjidViewModel
import com.example.aplikasiwisatarohul.ui.penginapan.PenginapanViewModel
import com.example.aplikasiwisatarohul.ui.spbu.SpbuViewModel
import com.example.aplikasiwisatarohul.ui.travel.TravelViewModel
import com.example.aplikasiwisatarohul.ui.wisata.WisataViewModel

class ViewModelFactory private constructor(private val appRepository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EventViewModel::class.java) -> {
                EventViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(AtmViewModel::class.java) -> {
                AtmViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(BeritaViewModel::class.java) -> {
                BeritaViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(MasjidViewModel::class.java) -> {
                MasjidViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(SpbuViewModel::class.java) -> {
                SpbuViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(WisataViewModel::class.java) -> {
                WisataViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(TravelViewModel::class.java) -> {
                TravelViewModel(appRepository) as T
            }
            modelClass.isAssignableFrom(PenginapanViewModel::class.java) -> {
                PenginapanViewModel(appRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}