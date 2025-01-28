package com.example.pamuas_080.ui.viewmodel.villa

import com.example.pamuas_080.repository.VillaRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.DetailVillaResponse
import com.example.pamuas_080.model.Villa
import kotlinx.coroutines.launch

class DetailVillaViewModel(
    private val villaRepository: VillaRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailVillaUiState())
        private set

    fun fetchDetailVilla(id_villa: Int) {  // Ubah id_villa menjadi Int
        viewModelScope.launch {
            uiState = DetailVillaUiState(isLoading = true)
            try {
                val response: DetailVillaResponse = villaRepository.getVillaById(id_villa)  // id_villa bertipe Int
                // Pastikan response.data tidak null
                val villaData = response.data
                if (villaData != null) {
                    uiState = DetailVillaUiState(detailVillaUiEvent = villaData.toDetailVillaEvent())
                } else {
                    uiState = DetailVillaUiState(isError = true, errorMessage = "Data villa tidak ditemukan")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailVillaUiState(isError = true, errorMessage = "Gagal mengambil detail: ${e.message}")
            }
        }
    }
}

data class DetailVillaUiState(
    val detailVillaUiEvent: DetailVillaUiEvent = DetailVillaUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailVillaUiEvent != DetailVillaUiEvent()
}

data class DetailVillaUiEvent(
    val id_villa: Int = 0,  // id_villa sekarang bertipe Int
    val nama_villa: String = "",
    val alamat: String = "",
    val kamar_tersedia: Int = 0  // kamar_tersedia bertipe Int
)

// Fungsi ekstensi untuk mengonversi Villa ke DetailVillaUiEvent
fun Villa.toDetailVillaEvent(): DetailVillaUiEvent {
    return DetailVillaUiEvent(
        id_villa = this.id_villa, // Pastikan id_villa tetap bertipe Int
        nama_villa = this.nama_villa,
        alamat = this.alamat,
        kamar_tersedia = this.kamar_tersedia // kamar_tersedia tetap bertipe Int
    )
}

