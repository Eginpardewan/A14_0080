package com.example.pamuas_080.ui.viewmodel.review

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.repository.ReviewRepository
import com.example.pamuas_080.ui.view.review.DestinasiUpdateReview
import kotlinx.coroutines.launch

class UpdateReviewViewModel(
    savedStateHandle: SavedStateHandle,
    private val reviewRepository: ReviewRepository
): ViewModel() {
    var updateReviewUiState by mutableStateOf(InsertReviewUiState())
        private set

    // Mengubah _id_review menjadi Int sesuai dengan tipe data di model
    private val _id_review: Int = checkNotNull(savedStateHandle[DestinasiUpdateReview.ID_REVIEW])

    init {
        // Mengambil data review dari repository berdasarkan id_review yang berupa Int
        viewModelScope.launch {
            val review = reviewRepository.getReviewById(_id_review)
            updateReviewUiState = review.toUiStateReview() // Transformasi ke UiState
        }
    }

    // Fungsi untuk memperbarui atau mengupdate UI state
    fun updateInsertReviewState(insertReviewUiEvent: InsertReviewUiEvent) {
        updateReviewUiState = InsertReviewUiState(insertReviewUiEvent = insertReviewUiEvent)
    }

    // Fungsi untuk melakukan update review di repository
    suspend fun updateReview() {
        try {
            // Menggunakan id_review bertipe Int untuk update review
            reviewRepository.updateReview(_id_review, updateReviewUiState.insertReviewUiEvent.toReview())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
