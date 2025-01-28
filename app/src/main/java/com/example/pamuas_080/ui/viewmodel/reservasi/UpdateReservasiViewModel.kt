package com.example.pamuas_080.ui.viewmodel.reservasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.repository.ReservasiRepository
import com.example.pamuas_080.ui.navigation.DestinasiUpdateReservasi
import kotlinx.coroutines.launch

class UpdateReservasiViewModel(
    savedStateHandle: SavedStateHandle,
    private val reservasi: ReservasiRepository
) : ViewModel() {

    var updateReservasiUiState by mutableStateOf(InsertReservasiUiState())
        private set

    private val _id_reservasi: Int = checkNotNull(savedStateHandle[DestinasiUpdateReservasi.ID_RESERVASI])

    init {
        viewModelScope.launch {
            try {
                // Mengambil data reservasi berdasarkan id_reservasi dan mengubahnya ke UI state
                updateReservasiUiState = reservasi.getReservasiById(_id_reservasi)
                    .toUiStateReservasi()
            } catch (e: Exception) {
                e.printStackTrace()
                // Tangani error jika data tidak ditemukan
            }
        }
    }

    // Fungsi untuk memperbarui UI state saat ada perubahan pada form input
    fun updateInsertReservasiState(insertReservasiUiEvent: InsertReservasiUiEvent) {
        updateReservasiUiState = InsertReservasiUiState(insertReservasiUiEvent = insertReservasiUiEvent)
    }

    // Fungsi untuk memperbarui reservasi
    suspend fun updateReservasi() {
        viewModelScope.launch {
            try {
                reservasi.updateReservasi(_id_reservasi, updateReservasiUiState.insertReservasiUiEvent.toReservasi())
            } catch (e: Exception) {
                e.printStackTrace()
                // Tangani error saat update reservasi
            }
        }
    }
}
