package com.example.pamuas_080.repository

import com.example.pamuas_080.model.AllVillaResponse
import com.example.pamuas_080.model.DetailVillaResponse
import com.example.pamuas_080.model.Villa
import com.example.pamuas_080.service.VillaService
import okio.IOException

interface VillaRepository {
        suspend fun getAllVillas(): AllVillaResponse
        suspend fun insertVilla(villa: Villa)
        suspend fun updateVilla(id_villa: Int, villa: Villa)
        suspend fun deleteVilla(id_villa: Int)
        suspend fun getVillaById(id_villa: Int): DetailVillaResponse
}

class NetworkVillaRepository(
        private val villaService: VillaService
) : VillaRepository {

        override suspend fun insertVilla(villa: Villa) {
                villaService.insertVilla(villa)
        }

        override suspend fun updateVilla(id_villa: Int, villa: Villa) {
                villaService.updateVilla(id_villa, villa)
        }

        override suspend fun deleteVilla(id_villa: Int) {
                try {
                        val response = villaService.deleteVilla(id_villa)
                        if (!response.isSuccessful) {
                                throw IOException("Failed to delete villa. HTTP Status code: ${response.code()}")
                        } else {
                                println("Villa deleted successfully: ${response.message()}")
                        }
                } catch (e: Exception) {
                        throw e
                }
        }

        override suspend fun getAllVillas(): AllVillaResponse {
                return villaService.getAllVillas()
        }

        override suspend fun getVillaById(id_villa: Int): DetailVillaResponse {
                return villaService.getVillaById(id_villa)
        }
}