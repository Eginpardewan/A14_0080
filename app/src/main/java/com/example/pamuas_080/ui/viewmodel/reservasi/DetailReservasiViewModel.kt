package com.example.pamuas_080.ui.viewmodel.reservasi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.Reservasi
import com.example.pamuas_080.repository.ReservasiRepository
import kotlinx.coroutines.launch

class DetailReservasiViewModel(
    private val reservasiRepository: ReservasiRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailReservasiUiState())
        private set

    fun fetchDetailReservasi(id_reservasi: Int) {
        viewModelScope.launch {
            uiState = DetailReservasiUiState(isLoading = true)
            try {
                // Fetch the details of the pelanggan
                val reservasi = reservasiRepository.getReservasiById(id_reservasi)
                uiState = DetailReservasiUiState(detailReservasiUiEvent = reservasi.toDetailEvent()) // Mapping to UI Event
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailReservasiUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailReservasiUiState(
    val detailReservasiUiEvent: DetailReservasiUiEvent = DetailReservasiUiEvent(), // Changed to DetailUiEvent
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailReservasiUiEvent != DetailReservasiUiEvent()
}

data class DetailReservasiUiEvent(
    val id_reservasi: Int = 0,   // id_reservasi bertipe Int
    val id_villa: Int = 0,       // id_villa bertipe Int
    val id_pelanggan: Int = 0,   // id_pelanggan bertipe Int
    val nama_villa: String = "",
    val check_in: String = "",
    val check_out: String = "",
    val jumlah_kamar: Int = 0    // jumlah_kamar tetap bertipe Int
)


fun Reservasi.toDetailEvent(): DetailReservasiUiEvent {
    return DetailReservasiUiEvent(
        id_reservasi = this.id_reservasi,  // idReservasi sudah bertipe Int
        id_villa = this.id_villa,          // idVilla sudah bertipe Int
        id_pelanggan = this.id_pelanggan,  // idPelanggan sudah bertipe Int
        nama_villa = this.nama_villa,
        check_in = this.check_in,
        check_out = this.check_out,
        jumlah_kamar = this.jumlah_kamar  // jumlahKamar tetap bertipe Int
    )
}

