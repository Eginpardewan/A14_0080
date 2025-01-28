package com.example.pamuas_080.service

import com.example.pamuas_080.model.Pelanggan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PelangganService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("pelanggan")
    suspend fun getAllPelanggan(): List<Pelanggan>

    @POST("pelanggan/store")
    suspend fun insertPelanggan(@Body pelanggan: Pelanggan)

    @GET("pelanggan/{id_pelanggan}")
    suspend fun getPelangganById(@Path("id_pelanggan") id: Int): Pelanggan

    @PUT("pelanggan/{id_pelanggan}")
    suspend fun updatePelanggan(@Path("id_pelanggan") id: Int, @Body pelanggan: Pelanggan)

    @DELETE("pelanggan/{id_pelanggan}")
    suspend fun deletePelanggan(@Path("id_pelanggan") id: Int): Response<Void>
}