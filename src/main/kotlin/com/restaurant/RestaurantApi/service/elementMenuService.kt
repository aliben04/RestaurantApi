package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.ElementMenu
import com.restaurant.RestaurantApi.repository.ElementMenuRepository
import com.restaurant.RestaurantApi.model.DTO.ElementMenuDTO
import com.restaurant.RestaurantApi.model.DTO.ElementMenuRequist

@Service
class ElementMenuService(private val elementMenuRepository: ElementMenuRepository) {

    fun getAllElements(): List<ElementMenuDTO> = elementMenuRepository.findAll().map { it.toDTO() }

    fun getElementById(id: Long): ElementMenuDTO =
        elementMenuRepository.findById(id).orElseThrow { RuntimeException("Élément non trouvé") }.toDTO()

    fun saveElement(request: ElementMenuRequist): ElementMenuDTO {
        val elementMenu = ElementMenu(nom = request.nom, prix = request.prix, description = request.description)
        val saved = elementMenuRepository.save(elementMenu)
        return saved.toDTO()
    }

    fun updateElement(id: Long, updated: ElementMenuRequist): ElementMenuDTO {
        val existing = elementMenuRepository.findById(id).orElseThrow { RuntimeException("Élément non trouvé") }
        existing.nom = updated.nom
        existing.prix = updated.prix
        existing.description = updated.description
        return elementMenuRepository.save(existing).toDTO()
    }

    fun deleteElement(id: Long) = elementMenuRepository.deleteById(id)

    private fun ElementMenu.toDTO(): ElementMenuDTO = ElementMenuDTO(
        id = this.id,
        nom = this.nom,
        prix = this.prix,
        description = this.description
    )

}
