package com.example.pamuas_080.repository

import com.example.pamuas_080.model.Reservasi
import com.example.pamuas_080.service.ReservasiService
import java.io.IOException

interface ReservasiRepository {
    suspend fun getAllReservasi(): List<Reservasi>
    suspend fun insertReservasi(reservasi: Reservasi)
    suspend fun updateReservasi(id_reservasi: Int, reservasi: Reservasi)
    suspend fun deleteReservasi(id_reservasi: Int)
    suspend fun getReservasiById(id_reservasi: Int): Reservasi
}

class NetworkReservasiRepository(
    private val reservasiService: ReservasiService
) : ReservasiRepository {

    override suspend fun insertReservasi(reservasi: Reservasi) {
        try {
            reservasiService.insertReservasi(reservasi)
        } catch (e: Exception) {
            throw IOException("Failed to insert reservasi: ${e.message}", e)
        }
    }

    override suspend fun updateReservasi(id_reservasi: Int, reservasi: Reservasi) {
        try {
            reservasiService.updateReservasi(id_reservasi, reservasi)
        } catch (e: Exception) {
            throw IOException("Failed to update reservasi with ID: $id_reservasi", e)
        }
    }

    override suspend fun deleteReservasi(id_reservasi: Int) {
        try {
            val response = reservasiService.deleteReservasi(id_reservasi)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete reservasi with ID: $id_reservasi. HTTP Status code: ${response.code()}")
            }
        } catch (e: Exception) {
            throw IOException("Failed to delete reservasi with ID: $id_reservasi: ${e.message}", e)
        }
    }

    override suspend fun getAllReservasi(): List<Reservasi> {
        return try {
            reservasiService.getAllReservasi()
        } catch (e: Exception) {
            throw IOException("Failed to fetch reservasi list: ${e.message}", e)
        }
    }

    override suspend fun getReservasiById(id_reservasi: Int): Reservasi {
        return try {
            reservasiService.getReservasiById(id_reservasi)
        } catch (e: Exception) {
            throw IOException("Failed to fetch reservasi with ID: $id_reservasi", e)
        }
    }
}
