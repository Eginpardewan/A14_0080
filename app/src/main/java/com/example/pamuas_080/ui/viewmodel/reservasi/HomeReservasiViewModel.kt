package com.example.pamuas_080.ui.viewmodel.reservasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamuas_080.model.Reservasi
import com.example.pamuas_080.repository.ReservasiRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeReservasiUiState {
    data class Success(val reservasi: List<Reservasi>) : HomeReservasiUiState()
    object Error : HomeReservasiUiState()
    object Loading : HomeReservasiUiState()
}

class HomeReservasiViewModel(
    private val reservasiRepository: ReservasiRepository
) : ViewModel() {

    var reservasiUiState: HomeReservasiUiState by mutableStateOf(HomeReservasiUiState.Loading)
        private set

    init {
        getReservasi()
    }

    fun getReservasi() {
        viewModelScope.launch {
            reservasiUiState = HomeReservasiUiState.Loading
            reservasiUiState = try {
                HomeReservasiUiState.Success(reservasiRepository.getAllReservasi())
            } catch (e: IOException) {
                HomeReservasiUiState.Error
            } catch (e: HttpException) {
                HomeReservasiUiState.Error
            }
        }
    }

    fun deleteReservasi(id: Int) {
        viewModelScope.launch {
            try {
                reservasiRepository.deleteReservasi(id)
                // Refresh the data after deletion
                getReservasi()
            } catch (e: IOException) {
                reservasiUiState = HomeReservasiUiState.Error
            } catch (e: HttpException) {
                reservasiUiState = HomeReservasiUiState.Error
            }
        }
    }
}
