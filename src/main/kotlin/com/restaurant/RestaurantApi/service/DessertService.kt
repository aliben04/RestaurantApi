package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.Dessert
import com.restaurant.RestaurantApi.model.DTO.DessertDto
import com.restaurant.RestaurantApi.model.DTO.DessertRequist
import com.restaurant.RestaurantApi.repository.DessertRepository

@Service
class DessertService(private val dessertRepository: DessertRepository) {

    fun saveDessert(dessert: DessertRequist):DessertDto{
        val dessert= Dessert(nom = dessert.nom, prix = dessert.prix, description = dessert.description, calories = dessert.calories, estServiChaud = dessert.estServiChaud,)
        return dessertRepository.save(dessert).toDTO()
    }


    fun getDesserts(): List<DessertDto> = dessertRepository.findAll().map { it.toDTO() }

    fun getDesserById(id: Long):DessertDto=dessertRepository.findById(id).
    orElseThrow{NoSuchElementException("Dessert avec id=$id non trouvée")}.toDTO()

    fun updateDessert(id:Long,newdessert: DessertRequist):DessertDto{
        val existing=dessertRepository.findById(id).orElseThrow { RuntimeException("Dessert non trouvé") }
        existing.nom = newdessert.nom
        existing.prix = newdessert.prix
        existing.description = newdessert.description
        existing.calories=newdessert.calories
        existing.estServiChaud=newdessert.estServiChaud

        return  dessertRepository.save(existing).toDTO()

    }

    fun deleteDessert(id:Long) = dessertRepository.deleteById(id)

        private fun Dessert.toDTO(): DessertDto = DessertDto(
            id = this.id,
            nom = this.nom,
            prix = this.prix,
            description = this.description,
            calories=this.calories,
            estServiChaud=this.estServiChaud
    )


}
