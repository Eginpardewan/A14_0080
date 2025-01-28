package com.example.pamuas_080.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pamuas_080.VillaApp
import com.example.pamuas_080.ui.viewmodel.pelanggan.DetailPelangganViewModel
import com.example.pamuas_080.ui.viewmodel.pelanggan.HomePelangganViewModel
import com.example.pamuas_080.ui.viewmodel.pelanggan.InsertPelangganViewModel
import com.example.pamuas_080.ui.viewmodel.pelanggan.UpdatePelangganViewModel
import com.example.pamuas_080.ui.viewmodel.reservasi.DetailReservasiViewModel
import com.example.pamuas_080.ui.viewmodel.reservasi.HomeReservasiViewModel
import com.example.pamuas_080.ui.viewmodel.reservasi.InsertReservasiViewModel
import com.example.pamuas_080.ui.viewmodel.reservasi.UpdateReservasiViewModel
import com.example.pamuas_080.ui.viewmodel.review.DetailReviewViewModel
import com.example.pamuas_080.ui.viewmodel.review.HomeReviewViewModel
import com.example.pamuas_080.ui.viewmodel.review.InsertReviewViewModel
import com.example.pamuas_080.ui.viewmodel.review.UpdateReviewViewModel
import com.example.pamuas_080.ui.viewmodel.villa.DetailVillaViewModel
import com.example.pamuas_080.ui.viewmodel.villa.HomeVillaViewModel
import com.example.pamuas_080.ui.viewmodel.villa.InsertVillaViewModel
import com.example.pamuas_080.ui.viewmodel.villa.UpdateVillaViewModel

object PenyediaViewModel{
    val Factory = viewModelFactory {
        initializer { HomePelangganViewModel(aplikasiVilla().container.pelangganRepository) }
        initializer { InsertPelangganViewModel(aplikasiVilla().container.pelangganRepository) }
        initializer { DetailPelangganViewModel(aplikasiVilla().container.pelangganRepository) }
        initializer { UpdatePelangganViewModel(createSavedStateHandle(),aplikasiVilla().container.pelangganRepository) }
        initializer { HomeVillaViewModel(aplikasiVilla().container.villaRepository) }
        initializer { InsertVillaViewModel(aplikasiVilla().container.villaRepository) }
        initializer { DetailVillaViewModel(aplikasiVilla().container.villaRepository) }
        initializer { UpdateVillaViewModel(createSavedStateHandle(),aplikasiVilla().container.villaRepository) }
        initializer { HomeReservasiViewModel(aplikasiVilla().container.reservasiRepository) }
        initializer { InsertReservasiViewModel(aplikasiVilla().container.reservasiRepository) }
        initializer { DetailReservasiViewModel(aplikasiVilla().container.reservasiRepository) }
        initializer { UpdateReservasiViewModel(createSavedStateHandle(),aplikasiVilla().container.reservasiRepository) }
        initializer { HomeReviewViewModel(aplikasiVilla().container.reviewRepository) }
        initializer { InsertReviewViewModel(aplikasiVilla().container.reviewRepository) }
        initializer { DetailReviewViewModel(aplikasiVilla().container.reviewRepository) }
        initializer { UpdateReviewViewModel(createSavedStateHandle(),aplikasiVilla().container.reviewRepository) }
    }
}

fun CreationExtras.aplikasiVilla(): VillaApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VillaApp)