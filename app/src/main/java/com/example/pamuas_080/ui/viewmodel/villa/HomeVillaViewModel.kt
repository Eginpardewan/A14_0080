package com.example.pamuas_080.ui.viewmodel.villa

import com.example.pamuas_080.repository.VillaRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamuas_080.model.Villa
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeVillaUiState {
    data class Success(val villa: List<Villa>) : HomeVillaUiState()
    data class Error(val message: String) : HomeVillaUiState() // Error state dengan pesan
    object Loading : HomeVillaUiState()
}

class HomeVillaViewModel(
    private val villaRepository: VillaRepository
) : ViewModel() {

    var villaUiState: HomeVillaUiState by mutableStateOf(HomeVillaUiState.Loading)
        private set

    init {
        getVilla()
    }

    fun getVilla() {
        viewModelScope.launch {
            villaUiState = HomeVillaUiState.Loading
            villaUiState = try {
                val response = villaRepository.getAllVillas() // Mengambil AllVillaResponse
                HomeVillaUiState.Success(response.data) // Menggunakan response.data untuk mendapatkan List<Villa>
            } catch (e: IOException) {
                HomeVillaUiState.Error("Network error: ${e.message}") // Menambahkan pesan error
            } catch (e: HttpException) {
                HomeVillaUiState.Error("Server error: ${e.message}") // Menambahkan pesan error
            }
        }
    }

    fun deleteVilla(id: Int) {
        viewModelScope.launch {
            try {
                villaRepository.deleteVilla(id)
                // Refresh the data after deletion
                getVilla()
            } catch (e: IOException) {
                villaUiState = HomeVillaUiState.Error("Network error: ${e.message}") // Menambahkan pesan error
            } catch (e: HttpException) {
                villaUiState = HomeVillaUiState.Error("Server error: ${e.message}") // Menambahkan pesan error
            }
        }
    }
}
