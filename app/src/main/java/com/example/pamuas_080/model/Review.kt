package com.example.pamuas_080.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id_review: Int,        // Int untuk ID Review
    val id_reservasi: Int,     // Int untuk ID Reservasi
    val nilai: String,            // Int untuk nilai review
    val komentar: String       // String untuk komentar
)

@Serializable
data class AllReviewsResponse(
    val status: Boolean,
    val message: String,
    val data: List<Review>
)

@Serializable
data class DetailReviewResponse(
    val status: Boolean,
    val message: String,
    val data: Review
)
