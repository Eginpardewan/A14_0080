package com.example.pamuas_080.ui.viewmodel.reservasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.Reservasi
import com.example.pamuas_080.repository.ReservasiRepository
import kotlinx.coroutines.launch

class InsertReservasiViewModel(
    private val reservasiRepository: ReservasiRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertReservasiUiState())
        private set

    fun updateInsertReservasiState(insertReservasiUiEvent: InsertReservasiUiEvent) {
        uiState = InsertReservasiUiState(insertReservasiUiEvent = insertReservasiUiEvent)
    }

    fun insertReservasi() {
        viewModelScope.launch {
            try {
                reservasiRepository.insertReservasi(uiState.insertReservasiUiEvent.toReservasi())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertReservasiUiState(
    val insertReservasiUiEvent: InsertReservasiUiEvent = InsertReservasiUiEvent()
)

data class InsertReservasiUiEvent(
    val id_reservasi: Int = 0,
    val id_villa: Int = 0,
    val id_pelanggan: Int = 0,
    val nama_pelanggan: String = "",
    val nama_villa: String = "",
    val check_in: String = "",
    val check_out: String = "",
    val jumlah_kamar: Int = 0
)

data class Reservasi(
    val id_reservasi: Int,
    val id_villa: Int,
    val id_pelanggan: Int,
    val nama_villa: String,
    val check_in: String,
    val check_out: String,
    val jumlah_kamar: Int
)

// Mengonversi InsertReservasiUiEvent ke model Reservasi
fun InsertReservasiUiEvent.toReservasi(): Reservasi = Reservasi(
    id_reservasi = id_reservasi,
    id_villa = id_villa,
    id_pelanggan = id_pelanggan,
    nama_villa = nama_villa,
    check_in = check_in,
    check_out = check_out,
    jumlah_kamar = jumlah_kamar
)

// Mengonversi model Reservasi ke InsertReservasiUiState
fun Reservasi.toUiStateReservasi(): InsertReservasiUiState = InsertReservasiUiState(
    insertReservasiUiEvent = this.toInsertReservasiUiEvent()
)

// Mengonversi model Reservasi ke InsertReservasiUiEvent
fun Reservasi.toInsertReservasiUiEvent(): InsertReservasiUiEvent = InsertReservasiUiEvent(
    id_reservasi = id_reservasi,
    id_villa = id_villa,
    id_pelanggan = id_pelanggan,
    nama_villa = nama_villa,
    check_in = check_in,
    check_out = check_out,
    jumlah_kamar = jumlah_kamar
)
