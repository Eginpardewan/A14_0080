package com.example.pamuas_080.ui.viewmodel.review

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pamuas_080.model.Review
import com.example.pamuas_080.repository.ReviewRepository
import kotlinx.coroutines.launch

class DetailReviewViewModel(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    var uiState by mutableStateOf(DetailReviewUiState())
        private set

    fun fetchDetailReview(id_review: Int) {  // Ubah ke Int
        viewModelScope.launch {
            uiState = DetailReviewUiState(isLoading = true)
            try {
                val review = reviewRepository.getReviewById(id_review) // Ubah ke Int
                uiState = DetailReviewUiState(detailReviewUiEvent = review.toDetailEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailReviewUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailReviewUiState(
    val detailReviewUiEvent: DetailReviewUiEvent = DetailReviewUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
)

data class DetailReviewUiEvent(
    val id_review: Int = 0,  // Ubah id_review menjadi Int
    val id_reservasi: Int = 0,  // Ubah id_reservasi menjadi Int
    val nilai: String = "",  // Ubah nilai menjadi Int
    val komentar: String = ""
)


fun Review.toDetailEvent(): DetailReviewUiEvent {
    return DetailReviewUiEvent(
        id_review = this.id_review,  // idReview sudah bertipe Int
        id_reservasi = this.id_reservasi,  // idReservasi sudah bertipe Int
        nilai = this.nilai,  // nilai sudah bertipe Int
        komentar = this.komentar
    )
}

