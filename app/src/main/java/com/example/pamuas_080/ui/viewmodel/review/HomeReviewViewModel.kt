package com.example.pamuas_080.ui.viewmodel.review

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.pamuas_080.model.Review
import com.example.pamuas_080.repository.ReviewRepository
import kotlinx.coroutines.launch
import okio.IOException

sealed class HomeReviewUiState {
    data class Success(val review: List<Review>) : HomeReviewUiState()
    object Error : HomeReviewUiState()
    object Loading : HomeReviewUiState()
}

class HomeReviewViewModel(
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    var reviewUiState: HomeReviewUiState by mutableStateOf(HomeReviewUiState.Loading)
        private set

    init {
        getReview()
    }

    fun getReview() {
        viewModelScope.launch {
            reviewUiState = HomeReviewUiState.Loading
            reviewUiState = try {
                HomeReviewUiState.Success(reviewRepository.getAllReviews())
            } catch (e: IOException) {
                HomeReviewUiState.Error
            } catch (e: HttpException) {
                HomeReviewUiState.Error
            }
        }
    }
}
