package com.example.pamuas_080.ui.viewmodel.pelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.repository.PelangganRepository
import com.example.pamuas_080.ui.navigation.DestinasiUpdatePelanggan
import kotlinx.coroutines.launch

class UpdatePelangganViewModel(
    savedStateHandle: SavedStateHandle,
    private val pelanggan: PelangganRepository
): ViewModel() {
    var updatePelangganUiState by mutableStateOf(InsertPelangganUiState())
        private set

    private val _id_pelanggan: Int = checkNotNull(savedStateHandle[DestinasiUpdatePelanggan.ID_PELANGGAN])

    init {
        viewModelScope.launch {
            updatePelangganUiState = pelanggan.getPelangganById(_id_pelanggan)
                .toUiStatePelanggan()
        }
    }

    fun updateInsertPelangganState(insertPelangganUiEvent: InsertPelangganUiEvent) {
        updatePelangganUiState = InsertPelangganUiState(insertPelangganUiEvent = insertPelangganUiEvent)
    }

    suspend fun updatePelanggan() {
        viewModelScope.launch {
            try {
                pelanggan.updatePelanggan(_id_pelanggan, updatePelangganUiState.insertPelangganUiEvent.toPelanggan())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}