package com.restaurant.RestaurantApi.service
import com.restaurant.RestaurantApi.model.DTO.platprincipalDto
import com.restaurant.RestaurantApi.model.DTO.platPrincipalRequist
import com.restaurant.RestaurantApi.model.PlatPrincipal
import com.restaurant.RestaurantApi.repository.PlatPrincipalRepostory
import org.springframework.stereotype.Service

@Service
class PlatPrincipalService(private val platPrincipalRepostory: PlatPrincipalRepostory) {
    fun getPlatsprincipal():List<PlatPrincipal> = platPrincipalRepostory.findAll()

    fun getplatById(id:Long):PlatPrincipal = platPrincipalRepostory.findById(id)
        .orElseThrow{ NoSuchElementException("PlatPrincipal avec id=$id non trouvée") }

    fun savePlatPrincipal(platPrincipal: platPrincipalRequist): PlatPrincipal{
        val plat= PlatPrincipal(nom = platPrincipal.nom, prix = platPrincipal.prix, description = platPrincipal.description,tempsPreparationMinutes=platPrincipal.tempsPreparationMinutes)
        return platPrincipalRepostory.save(plat)
    }
    fun uppdatePlatPrincipal(id:Long,platPrincipal: platPrincipalRequist):PlatPrincipal{
        val existing=platPrincipalRepostory.findById(id).
        orElseThrow( { RuntimeException("PlatPrincipal non trouvé") })
        existing.nom = platPrincipal.nom
        existing.prix = platPrincipal.prix
        existing.description = platPrincipal.description
        existing.tempsPreparationMinutes=platPrincipal.tempsPreparationMinutes
        return platPrincipalRepostory.save(existing)
    }
    fun deletePlatPrincipal(id:Long)=platPrincipalRepostory.deleteById(id)

}