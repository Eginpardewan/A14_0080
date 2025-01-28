package com.example.pamuas_080.ui.viewmodel.villa

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.toUiStateVilla
import com.example.pamuas_080.repository.VillaRepository
import com.example.pamuas_080.ui.navigation.DestinasiUpdateVilla
import kotlinx.coroutines.launch

class UpdateVillaViewModel(
    savedStateHandle: SavedStateHandle,
    private val villaRepository: VillaRepository
) : ViewModel() {

    var updateVillaUiState by mutableStateOf(InsertVillaUiState()) // Periksa konstruktor default
        private set

    // Menyesuaikan dengan tipe data id_villa yang Int
    private val _idVilla: Int = checkNotNull(savedStateHandle[DestinasiUpdateVilla.ID_VILLA]) // Mengasumsikan ID sebagai Int

    init {
        viewModelScope.launch {
            try {
                // Mendapatkan detail villa berdasarkan ID yang sudah diperbaiki
                val detailVillaResponse = villaRepository.getVillaById(_idVilla)
                updateVillaUiState = detailVillaResponse.toUiStateVilla()
            } catch (e: Exception) {
                // Error handling: tampilkan UI error
                e.printStackTrace()
            }
        }
    }

    fun updateInsertVillaState(insertVillaUiEvent: InsertVillaUiEvent) {
        updateVillaUiState = InsertVillaUiState(insertVillaUiEvent = insertVillaUiEvent)
    }

    fun updateVilla() {
        viewModelScope.launch {
            try {
                // Mengubah data untuk update menggunakan data yang sudah diperbarui
                val updatedVilla = updateVillaUiState.insertVillaUiEvent.toVilla()
                villaRepository.updateVilla(_idVilla, updatedVilla) // Pastikan ID adalah tipe yang sesuai
            } catch (e: Exception) {
                // Error handling: tampilkan UI error
                e.printStackTrace()
            }
        }
    }
}

