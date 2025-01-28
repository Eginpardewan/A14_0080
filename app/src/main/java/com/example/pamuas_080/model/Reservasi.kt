package com.example.pamuas_080.model

import kotlinx.serialization.Serializable

@Serializable
data class Reservasi(
    val id_reservasi: Int,        // Mengganti jadi Int untuk ID
    val id_villa: Int,            // Mengganti jadi Int untuk ID Villa
    val id_pelanggan: Int,        // Mengganti jadi Int untuk ID Pelanggan
    val nama_villa: String,
    val check_in: String,         // Jika formatnya string ISO (yyyy-MM-dd)
    val check_out: String,        // Jika formatnya string ISO (yyyy-MM-dd)
    val jumlah_kamar: Int         // Mengganti jadi Int untuk jumlah kamar
)

@Serializable
data class AllReservasiResponse(
    val status: Boolean,
    val message: String,
    val data: List<Reservasi>
)

@Serializable
data class DetailReservasiResponse(
    val status: Boolean,
    val message: String,
    val data: Reservasi
)
