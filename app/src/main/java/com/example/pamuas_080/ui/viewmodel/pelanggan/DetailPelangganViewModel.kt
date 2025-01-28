package com.example.pamuas_080.ui.viewmodel.pelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.Pelanggan
import com.example.pamuas_080.repository.PelangganRepository
import kotlinx.coroutines.launch

class DetailPelangganViewModel(
    private val pelangganRepository: PelangganRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailPelangganUiState())
        private set

    fun fetchDetailPelanggan(id_pelanggan: Int) {
        viewModelScope.launch {
            uiState = DetailPelangganUiState(isLoading = true)
            try {
                // Fetch the details of the pelanggan
                val pelanggan = pelangganRepository.getPelangganById(id_pelanggan)
                uiState = DetailPelangganUiState(detailPelangganUiEvent = pelanggan.toDetailEvent()) // Mapping to UI Event
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailPelangganUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailPelangganUiState(
    val detailPelangganUiEvent: DetailPelangganUiEvent = DetailPelangganUiEvent(), // Changed to DetailUiEvent
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailPelangganUiEvent != DetailPelangganUiEvent()
}

data class DetailPelangganUiEvent(
    val id_pelanggan: Int = 0,
    val nama_pelanggan: String = "",
    val no_hp: String = ""
)

fun Pelanggan.toDetailEvent(): DetailPelangganUiEvent {
    return DetailPelangganUiEvent(
        id_pelanggan = id_pelanggan,
        nama_pelanggan = nama_pelanggan,
        no_hp = no_hp
    )
}
