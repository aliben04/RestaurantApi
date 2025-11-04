package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.Dessert
import com.restaurant.RestaurantApi.model.DTO.DessertRequist
import com.restaurant.RestaurantApi.repository.DessertRepository

@Service
class DessertService(private val dessertRepository: DessertRepository) {

    fun saveDessert(dessert: DessertRequist):Dessert{
        val dessert= Dessert(nom = dessert.nom, prix = dessert.prix, description = dessert.description, calories = dessert.calories, estServiChaud = dessert.estServiChaud,)
        return dessertRepository.save(dessert)
    }


    fun getDesserts(): List<Dessert> = dessertRepository.findAll()

    fun getDesserById(id: Long):Dessert=dessertRepository.findById(id).
    orElseThrow{NoSuchElementException("Dessert avec id=$id non trouvée")}

    fun updateDessert(id:Long,newdessert: DessertRequist):Dessert{
        val existing=dessertRepository.findById(id).orElseThrow { RuntimeException("Dessert non trouvé") }
        existing.nom = newdessert.nom
        existing.prix = newdessert.prix
        existing.description = newdessert.description
        existing.calories=newdessert.calories
        existing.estServiChaud=newdessert.estServiChaud

        return  dessertRepository.save(existing)

    }

    fun deleteDessert(id:Long) = dessertRepository.deleteById(id)



}
