package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.ElementMenu
import com.restaurant.RestaurantApi.repository.ElementMenuRepository
import com.restaurant.RestaurantApi.model.DTO.ElementMenuDTO
import com.restaurant.RestaurantApi.model.DTO.ElementMenuRequist

@Service
class ElementMenuService(private val elementMenuRepository: ElementMenuRepository) {

    fun getAllElements(): List<ElementMenu> = elementMenuRepository.findAll()

    fun getElementById(id: Long): ElementMenu =
        elementMenuRepository.findById(id).orElseThrow { RuntimeException("Élément non trouvé") }

    fun saveElement(request: ElementMenuRequist): ElementMenu {
        val elementMenu = ElementMenu(nom = request.nom, prix = request.prix, description = request.description)
        val saved = elementMenuRepository.save(elementMenu)
        return saved
    }

    fun updateElement(id: Long, updated: ElementMenuRequist): ElementMenu {
        val existing = elementMenuRepository.findById(id).orElseThrow { RuntimeException("Élément non trouvé") }
        existing.nom = updated.nom
        existing.prix = updated.prix
        existing.description = updated.description
        return elementMenuRepository.save(existing)
    }

    fun deleteElement(id: Long) = elementMenuRepository.deleteById(id)

}
