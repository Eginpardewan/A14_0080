package com.example.pamuas_080.ui.viewmodel.pelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamuas_080.model.Pelanggan
import com.example.pamuas_080.repository.PelangganRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeUiState {
    data class Success(val pelanggan: List<Pelanggan>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomePelangganViewModel(
    private val pelangganRepository: PelangganRepository
) : ViewModel() {

    var pelangganUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getPelanggan()
    }

    fun getPelanggan() {
        viewModelScope.launch {
            pelangganUiState = HomeUiState.Loading
            pelangganUiState = try {
                HomeUiState.Success(pelangganRepository.getAllPelanggan())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deletePelanggan(id: Int) {
        viewModelScope.launch {
            try {
                pelangganRepository.deletePelanggan(id)
                // Refresh the data after deletion
                getPelanggan()
            } catch (e: IOException) {
                pelangganUiState = HomeUiState.Error
            } catch (e: HttpException) {
                pelangganUiState = HomeUiState.Error
            }
        }
    }
}
