package com.example.pamuas_080.repository

import com.example.pamuas_080.model.Pelanggan
import com.example.pamuas_080.service.PelangganService
import java.io.IOException
import retrofit2.HttpException

interface PelangganRepository {
    suspend fun getAllPelanggan(): List<Pelanggan>
    suspend fun insertPelanggan(pelanggan: Pelanggan)
    suspend fun updatePelanggan(id_pelanggan: Int, pelanggan: Pelanggan)
    suspend fun deletePelanggan(id_pelanggan: Int)
    suspend fun getPelangganById(id_pelanggan: Int): Pelanggan
}

class NetworkPelangganRepository(
    private val pelangganService: PelangganService
) : PelangganRepository {

    override suspend fun insertPelanggan(pelanggan: Pelanggan) {
        try {
            pelangganService.insertPelanggan(pelanggan)
        } catch (e: HttpException) {
            throw IOException("Failed to insert pelanggan: ${e.message()}")
        } catch (e: Exception) {
            throw IOException("An unexpected error occurred while inserting pelanggan", e)
        }
    }

    override suspend fun updatePelanggan(id_pelanggan: Int, pelanggan: Pelanggan) {
        try {
            pelangganService.updatePelanggan(id_pelanggan, pelanggan)
        } catch (e: HttpException) {
            throw IOException("Failed to update pelanggan: ${e.message()}")
        } catch (e: Exception) {
            throw IOException("An unexpected error occurred while updating pelanggan", e)
        }
    }

    override suspend fun deletePelanggan(id_pelanggan: Int) {
        try {
            val response = pelangganService.deletePelanggan(id_pelanggan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete pelanggan with ID: $id_pelanggan. HTTP Status code: ${response.code()}")
            }
        } catch (e: HttpException) {
            throw IOException("Failed to delete pelanggan with ID: $id_pelanggan. Error: ${e.message()}")
        } catch (e: Exception) {
            throw IOException("An unexpected error occurred while deleting pelanggan", e)
        }
    }

    override suspend fun getAllPelanggan(): List<Pelanggan> {
        try {
            return pelangganService.getAllPelanggan()
        } catch (e: HttpException) {
            throw IOException("Failed to fetch all pelanggan: ${e.message()}")
        } catch (e: Exception) {
            throw IOException("An unexpected error occurred while fetching all pelanggan", e)
        }
    }

    override suspend fun getPelangganById(idPelanggan: Int): Pelanggan {
        try {
            return pelangganService.getPelangganById(idPelanggan)
        } catch (e: HttpException) {
            throw IOException("Failed to fetch pelanggan with ID: $idPelanggan. Error: ${e.message()}")
        } catch (e: Exception) {
            throw IOException("An unexpected error occurred while fetching pelanggan by ID", e)
        }
    }
}
