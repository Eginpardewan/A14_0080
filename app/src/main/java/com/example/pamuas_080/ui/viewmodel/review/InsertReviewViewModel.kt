package com.example.pamuas_080.ui.viewmodel.review

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.Review
import com.example.pamuas_080.repository.ReviewRepository
import kotlinx.coroutines.launch

class InsertReviewViewModel(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    var uiState by mutableStateOf(InsertReviewUiState())
        private set

    fun updateInsertReviewState(insertReviewUiEvent: InsertReviewUiEvent) {
        uiState = InsertReviewUiState(insertReviewUiEvent = insertReviewUiEvent)
    }

    fun insertReview() {
        viewModelScope.launch {
            try {
                reviewRepository.insertReview(uiState.insertReviewUiEvent.toReview())
            } catch (e: Exception) {
                e.printStackTrace()
                // Penanganan kesalahan jika ada
            }
        }
    }
}

data class InsertReviewUiState(
    val insertReviewUiEvent: InsertReviewUiEvent = InsertReviewUiEvent()
)

data class InsertReviewUiEvent(
    val id_review: Int = 0,  // Menggunakan Int untuk id_review
    val id_reservasi: Int = 0,  // Menggunakan Int untuk id_reservasi
    val nilai: String = "",  // Menggunakan Int untuk nilai
    val komentar: String = ""
)

fun InsertReviewUiEvent.toReview(): Review = Review(
    id_review = id_review,
    id_reservasi = id_reservasi,
    nilai = nilai,
    komentar = komentar
)

fun Review.toUiStateReview(): InsertReviewUiState = InsertReviewUiState(
    insertReviewUiEvent = toInsertReviewUiEvent()
)

fun Review.toInsertReviewUiEvent(): InsertReviewUiEvent = InsertReviewUiEvent(
    id_review = id_review,
    id_reservasi = id_reservasi,
    nilai = nilai,
    komentar = komentar
)
