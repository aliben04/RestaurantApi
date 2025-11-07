package com.restaurant.RestaurantApi.service
import com.restaurant.RestaurantApi.model.DTO.platPrincipalRequist
import com.restaurant.RestaurantApi.model.PlatPrincipal
import com.restaurant.RestaurantApi.repository.PlatPrincipalRepostory
import org.springframework.stereotype.Service

/**
 * Service métier pour la gestion des **plats principaux**.
 *
 * Fournit les opérations CRUD :
 * - Création, lecture, mise à jour et suppression.
 *
 * Dépend du [PlatPrincipalRepostory] pour l’accès aux données.
 *
 * ### Exemple d'utilisation :
 * ```kotlin
 * val plat = platPrincipalService.savePlatPrincipal(
 *     platPrincipalRequist("Poulet Rôti", "Poulet rôti aux herbes", 25.0, 45)
 * )
 * ```
 */
@Service
class PlatPrincipalService(private val platPrincipalRepostory: PlatPrincipalRepostory) {
    /**
     * Récupère la liste complète des plats enregistrés.
     * @return Liste de tous les plats.
     */
    fun getPlatsprincipal():List<PlatPrincipal> = platPrincipalRepostory.findAll()
    /**
     * Recherche un plat par son identifiant.
     * @throws NoSuchElementException si aucun plat n’existe avec cet ID.
     */
    fun getplatById(id:Long):PlatPrincipal = platPrincipalRepostory.findById(id)
        .orElseThrow{ NoSuchElementException("PlatPrincipal avec id=$id non trouvée") }
    /**
     * Crée et enregistre un nouveau plat dans la base de données.
     * @param platPrincipal Données du plat reçues dans la requête.
     * @return Le plat sauvegardé avec son ID généré.
     */
    fun savePlatPrincipal(platPrincipal: platPrincipalRequist): PlatPrincipal{
        val plat= PlatPrincipal(nom = platPrincipal.nom, prix = platPrincipal.prix, description = platPrincipal.description,tempsPreparationMinutes=platPrincipal.tempsPreparationMinutes)
        return platPrincipalRepostory.save(plat)
    }
    /**
     * Met à jour les informations d’un plat existant.
     * @param id Identifiant du plat à modifier.
     * @param platPrincipal Données de mise à jour.
     * @return Le plat mis à jour.
     */
    fun uppdatePlatPrincipal(id:Long,platPrincipal: platPrincipalRequist):PlatPrincipal{
        val existing=platPrincipalRepostory.findById(id).
        orElseThrow( { RuntimeException("PlatPrincipal non trouvé") })
        existing.nom = platPrincipal.nom
        existing.prix = platPrincipal.prix
        existing.description = platPrincipal.description
        existing.tempsPreparationMinutes=platPrincipal.tempsPreparationMinutes
        return platPrincipalRepostory.save(existing)
    }
    /**
     * Supprime un plat existant par son identifiant.
     * @param id Identifiant du plat à supprimer.
     */
    fun deletePlatPrincipal(id:Long)=platPrincipalRepostory.deleteById(id)

}