package com.restaurant.RestaurantApi.service

import com.restaurant.RestaurantApi.model.Boisson
import com.restaurant.RestaurantApi.model.DTO.BoissonRequest
import com.restaurant.RestaurantApi.repository.BoissonRepository
import org.springframework.stereotype.Service

@Service
class BoissonService(private val boissonRepository: BoissonRepository) {

    fun saveBoisson(request: BoissonRequest): Boisson{
        val boisson = Boisson(nom = request.nom, prix = request.prix, description = request.description, volumeLitre = request.volumeLitre, contientAlcool = request.contientAlcool)
        return boissonRepository.save(boisson)
    }


    fun getAllBoissons(): List<Boisson> = boissonRepository.findAll()

    fun getBoissonById(id: Long): Boisson =
        boissonRepository.findById(id).orElseThrow { NoSuchElementException("Boisson avec id=$id non trouvée") }

    fun updateBoisson(id: Long,request: BoissonRequest): Boisson {
        val existing = getBoissonById(id)
        existing.nom = request.nom
        existing.prix = request.prix
        existing.description = request.description
        existing.volumeLitre=request.volumeLitre
        existing.contientAlcool=request.contientAlcool

        return boissonRepository.save(existing)

    }

    fun deleteBoisson(id: Long) = boissonRepository.deleteById(id)

}

