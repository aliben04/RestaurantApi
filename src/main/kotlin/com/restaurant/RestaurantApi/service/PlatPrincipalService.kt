package com.restaurant.RestaurantApi.service
import com.restaurant.RestaurantApi.model.DTO.platprincipalDto
import com.restaurant.RestaurantApi.model.DTO.platPrincipalRequist
import com.restaurant.RestaurantApi.model.PlatPrincipal
import com.restaurant.RestaurantApi.repository.PlatPrincipalRepostory
import org.springframework.stereotype.Service

@Service
class PlatPrincipalService(private val platPrincipalRepostory: PlatPrincipalRepostory) {
    fun getPlatsprincipal():List<platprincipalDto> = platPrincipalRepostory.findAll().map { it.toDTO() }

    fun getplatById(id:Long):platprincipalDto = platPrincipalRepostory.findById(id)
        .orElseThrow{ NoSuchElementException("PlatPrincipal avec id=$id non trouvée") }.toDTO()

    fun savePlatPrincipal(platPrincipal: platPrincipalRequist): platprincipalDto{
        val plat= PlatPrincipal(nom = platPrincipal.nom, prix = platPrincipal.prix, description = platPrincipal.description,tempsPreparationMinutes=platPrincipal.tempsPreparationMinutes)
        return platPrincipalRepostory.save(plat).toDTO()
    }
    fun uppdatePlatPrincipal(id:Long,platPrincipal: platPrincipalRequist):platprincipalDto{
        val existing=platPrincipalRepostory.findById(id).
        orElseThrow( { RuntimeException("PlatPrincipal non trouvé") })
        existing.nom = platPrincipal.nom
        existing.prix = platPrincipal.prix
        existing.description = platPrincipal.description
        existing.tempsPreparationMinutes=platPrincipal.tempsPreparationMinutes
        return platPrincipalRepostory.save(existing).toDTO()
    }
    fun deletePlatPrincipal(id:Long)=platPrincipalRepostory.deleteById(id)
    private fun PlatPrincipal.toDTO(): platprincipalDto = platprincipalDto(
        id = this.id,
        nom = this.nom,
        prix = this.prix,
        description = this.description,
        tempsPreparationMinutes=this.tempsPreparationMinutes
    )
}