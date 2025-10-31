package com.restaurant.RestaurantApi.service

import com.restaurant.RestaurantApi.model.Boisson
import com.restaurant.RestaurantApi.model.DTO.BoissonDTO
import com.restaurant.RestaurantApi.model.DTO.BoissonRequest
import com.restaurant.RestaurantApi.repository.BoissonRepository
import org.springframework.stereotype.Service

@Service
class BoissonService(private val boissonRepository: BoissonRepository) {

    fun saveBoisson(request: BoissonRequest): BoissonDTO{
        val boisson = Boisson(nom = request.nom, prix = request.prix, description = request.description, volumeLitre = request.volumeLitre, contientAlcool = request.contientAlcool)
        return boissonRepository.save(boisson).toDTO()
    }


    fun getAllBoissons(): List<BoissonDTO> = boissonRepository.findAll().map { it.toDTO() }

    fun getBoissonById(id: Long): BoissonDTO =
        boissonRepository.findById(id).orElseThrow { NoSuchElementException("Boisson avec id=$id non trouvée") }.toDTO()

    fun updateBoisson(id: Long,request: BoissonRequest): BoissonDTO {
        val existing = boissonRepository.findById(id).orElseThrow { RuntimeException("Boisson non trouvé") }
        existing.nom = request.nom
        existing.prix = request.prix
        existing.description = request.description
        existing.volumeLitre=request.volumeLitre
        existing.contientAlcool=request.contientAlcool

        return boissonRepository.save(existing).toDTO()

    }

    fun deleteBoisson(id: Long) = boissonRepository.deleteById(id)
    private fun Boisson.toDTO(): BoissonDTO = BoissonDTO(
        id = this.id,
        nom = this.nom,
        prix = this.prix,
        description = this.description,
        volumeLitre=this.volumeLitre,
        contientAlcool=this.contientAlcool
    )
}

