package com.example.pamuas_080.service

import com.example.pamuas_080.model.Reservasi
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReservasiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    // Mendapatkan semua reservasi
    @GET("reservasi")
    suspend fun getAllReservasi(): List<Reservasi>

    @GET("reservasi/{id_reservasi}")
    suspend fun getReservasiById(@Path("idReservasi") id: Int): Reservasi

    // Membuat reservasi baru
    @POST("reservasi/store")
    suspend fun insertReservasi(@Body reservasi: Reservasi)

    // Memperbarui reservasi berdasarkan ID
    @PUT("reservasi/{id_reservasi}")
    suspend fun updateReservasi(@Path("idReservasi") id: Int, @Body reservasi: Reservasi)

    // Menghapus reservasi berdasarkan ID
    @DELETE("reservasi/{id_reservasi}")
    suspend fun deleteReservasi(@Path("idReservasi") id: Int): retrofit2.Response<Void>
}