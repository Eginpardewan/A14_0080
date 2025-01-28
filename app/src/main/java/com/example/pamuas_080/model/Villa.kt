package com.example.pamuas_080.model

import com.example.pamuas_080.ui.viewmodel.villa.InsertVillaUiEvent
import com.example.pamuas_080.ui.viewmodel.villa.InsertVillaUiState
import kotlinx.serialization.Serializable

@Serializable
data class Villa(
    val id_villa: Int,       // Tetap menggunakan Int
    val nama_villa: String,
    val alamat: String,
    val kamar_tersedia: Int
)

@Serializable
data class AllVillaResponse(
    val status: Boolean,
    val message: String,
    val data: List<Villa>
)

@Serializable
data class DetailVillaResponse(
    val status: Boolean,
    val message: String,
    val data: Villa
)

fun DetailVillaResponse.toUiStateVilla(): InsertVillaUiState {
    return InsertVillaUiState(
        insertVillaUiEvent = InsertVillaUiEvent(
            id_villa = this.data.id_villa,        // idVilla tetap Int
            nama_villa = this.data.nama_villa,
            alamat = this.data.alamat,
            kamar_tersedia = this.data.kamar_tersedia
        )
    )
}

