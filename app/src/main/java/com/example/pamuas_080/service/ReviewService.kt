package com.example.pamuas_080.service

import com.example.pamuas_080.model.Review
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("review")
    suspend fun getAllReviews(): List<Review>

    // Mendapatkan detail review berdasarkan ID
    @GET("review/{id_review}")
    suspend fun getReviewById(@Path("id_review") id_review: Int): Review

    // Membuat review baru
    @POST("review/store")
    suspend fun insertReview(@Body review: Review)

    // Memperbarui review berdasarkan ID
    @PUT("review/{id_review}")
    suspend fun updateReview(@Path("idReview") id_review: Int, @Body review: Review)
}