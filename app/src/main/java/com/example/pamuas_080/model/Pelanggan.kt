package com.example.pamuas_080.model

import kotlinx.serialization.Serializable

@Serializable
data class Pelanggan(
    val id_pelanggan: Int,  // Mengganti menjadi Int jika id_pelanggan adalah integer
    val nama_pelanggan: String,
    val no_hp: String,
)

@Serializable
data class AllPelangganResponse(
    val status: Boolean,
    val message: String,
    val data: List<Pelanggan>
)

@Serializable
data class DetailPelangganResponse(
    val status: Boolean,
    val message: String,
    val data: Pelanggan
)

