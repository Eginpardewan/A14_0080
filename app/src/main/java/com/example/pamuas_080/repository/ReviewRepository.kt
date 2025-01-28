package com.example.pamuas_080.repository

import com.example.pamuas_080.model.Review
import com.example.pamuas_080.service.ReviewService
import java.io.IOException

interface ReviewRepository {
    suspend fun getAllReviews(): List<Review>
    suspend fun insertReview(review: Review)
    suspend fun updateReview(id_review: Int, review: Review) // Ubah ke Int
    suspend fun getReviewById(id_review: Int): Review // Ubah ke Int
}

class NetworkReviewRepository(
    private val reviewService: ReviewService
) : ReviewRepository {

    override suspend fun insertReview(review: Review) {
        try {
            reviewService.insertReview(review)
        } catch (e: Exception) {
            throw IOException("Failed to insert review", e)
        }
    }

    override suspend fun updateReview(id_review: Int, review: Review) { // Ubah ke Int
        try {
            reviewService.updateReview(id_review, review)
        } catch (e: Exception) {
            throw IOException("Failed to update review with ID: $id_review", e)
        }
    }

    override suspend fun getAllReviews(): List<Review> {
        return try {
            reviewService.getAllReviews()
        } catch (e: Exception) {
            throw IOException("Failed to fetch reviews", e)
        }
    }

    override suspend fun getReviewById(id_review: Int): Review { // Ubah ke Int
        return try {
            reviewService.getReviewById(id_review)
        } catch (e: Exception) {
            throw IOException("Failed to fetch review with ID: $id_review", e)
        }
    }
}
