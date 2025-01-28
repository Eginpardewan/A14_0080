package com.example.pamuas_080.ui.viewmodel.villa

import com.example.pamuas_080.repository.VillaRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.Villa
import kotlinx.coroutines.launch

class InsertVillaViewModel(
    private val villaRepository: VillaRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertVillaUiState())
        private set

    var errorMessage by mutableStateOf("") // Untuk menyimpan pesan error
        private set

    fun updateInsertVillaState(insertVillaUiEvent: InsertVillaUiEvent) {
        uiState = InsertVillaUiState(insertVillaUiEvent = insertVillaUiEvent)
    }

    fun insertVilla() {
        viewModelScope.launch {
            try {
                // Validasi input sebelum dikirim ke repository
                if (uiState.insertVillaUiEvent.isValid()) {
                    villaRepository.insertVilla(uiState.insertVillaUiEvent.toVilla())
                } else {
                    errorMessage = "Please fill all fields correctly." // Pesan error jika input tidak valid
                }
            } catch (e: Exception) {
                errorMessage = "An error occurred: ${e.message}" // Tangani error
                e.printStackTrace()
            }
        }
    }
}

data class InsertVillaUiState(
    val insertVillaUiEvent: InsertVillaUiEvent = InsertVillaUiEvent()
)

data class InsertVillaUiEvent(
    val id_villa: Int = 0,
    val nama_villa: String = "",
    val alamat: String = "",
    val kamar_tersedia: Int = 0
) {
    fun isValid(): Boolean {
        // Validasi id_villa dan kamar_tersedia tidak kosong atau nol
        return id_villa != 0 && nama_villa.isNotEmpty() && alamat.isNotEmpty() && kamar_tersedia != 0
    }
}

fun InsertVillaUiEvent.toVilla(): Villa = Villa(
    id_villa = id_villa,
    nama_villa = nama_villa,
    alamat = alamat,
    kamar_tersedia = kamar_tersedia
)

fun Villa.toUiStateVilla(): InsertVillaUiState = InsertVillaUiState(
    insertVillaUiEvent = toInsertVillaUiEvent()
)

fun Villa.toInsertVillaUiEvent(): InsertVillaUiEvent = InsertVillaUiEvent(
    id_villa = id_villa,  // Nama parameter konsisten
    nama_villa = nama_villa,
    alamat = alamat,
    kamar_tersedia = kamar_tersedia
)
