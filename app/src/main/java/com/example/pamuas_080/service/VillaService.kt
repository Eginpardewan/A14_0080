package com.example.pamuas_080.service

import com.example.pamuas_080.model.AllVillaResponse
import com.example.pamuas_080.model.DetailVillaResponse
import com.example.pamuas_080.model.Villa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VillaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("villas")
    suspend fun getAllVillas(): AllVillaResponse // Mengembalikan AllVillaResponse

    @POST("villas/store")
    suspend fun insertVilla(@Body villa: Villa)

    @GET("villas/{id_villa}")
    suspend fun getVillaById(@Path("id_villa") id_villa: Int): DetailVillaResponse // Mengembalikan DetailVillaResponse

    @PUT("villas/{id_villa}")
    suspend fun updateVilla(@Path("id_villa") id_villa: Int, @Body villa: Villa): Response<Villa> // Mengembalikan Response<Villa>

    @DELETE("villas/{id_villa}")
    suspend fun deleteVilla(@Path("id_villa") id_villa: Int): Response<Void>
}