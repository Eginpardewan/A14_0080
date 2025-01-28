package com.example.pamuas_080.dependenciesinjection

import com.example.pamuas_080.repository.*
import com.example.pamuas_080.service.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val villaRepository: VillaRepository
    val pelangganRepository: PelangganRepository
    val reservasiRepository: ReservasiRepository
    val reviewRepository: ReviewRepository
}

class VillaContainer : AppContainer {

    private val baseUrl = "http://10.0.2.2:3000/api/" // Ganti dengan IP jika perlu
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    private val villaService: VillaService by lazy {
        retrofit.create(VillaService::class.java)
    }

    private val pelangganService: PelangganService by lazy {
        retrofit.create(PelangganService::class.java)
    }

    private val reservasiService: ReservasiService by lazy {
        retrofit.create(ReservasiService::class.java)
    }

    private val reviewService: ReviewService by lazy {
        retrofit.create(ReviewService::class.java)
    }

    override val villaRepository: VillaRepository by lazy {
        NetworkVillaRepository(villaService)
    }

    override val pelangganRepository: PelangganRepository by lazy {
        NetworkPelangganRepository(pelangganService)
    }

    override val reservasiRepository: ReservasiRepository by lazy {
        NetworkReservasiRepository(reservasiService)
    }

    override val reviewRepository: ReviewRepository by lazy {
        NetworkReviewRepository(reviewService)
    }
}