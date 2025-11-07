package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.Dessert
import com.restaurant.RestaurantApi.model.DTO.DessertRequist
import com.restaurant.RestaurantApi.repository.DessertRepository
/**
 * Service métier gérant la logique applicative liée aux **desserts**.
 *
 * Fournit les opérations CRUD :
 * - Création d’un dessert à partir d’une requête DTO ([DessertRequist])
 * - Récupération de tous les desserts ou d’un dessert spécifique
 * - Mise à jour et suppression
 *
 * Utilisé par le contrôleur REST pour traiter les requêtes HTTP.
 *
 * ### Exemple d'utilisation :
 * ```kotlin
 * val dessert = dessertService.saveDessert(
 *     DessertRequist(
 *         nom = "Tiramisu",
 *         prix = 7.5,
 *         description = "Dessert italien au café et mascarpone",
 *         calories = 300,
 *         estServiChaud = false
 *     )
 * )
 * ```
 *
 * @property dessertRepository Composant d’accès aux données pour les entités [Dessert].
 */
@Service
class DessertService(private val dessertRepository: DessertRepository) {
    /**
     * Crée et enregistre un nouveau dessert dans la base de données.
     * @param dessert Données du dessert reçues dans la requête.
     * @return Le dessert sauvegardé avec son ID généré.
     */
    fun saveDessert(dessert: DessertRequist):Dessert{
        val dessert= Dessert(nom = dessert.nom, prix = dessert.prix, description = dessert.description, calories = dessert.calories, estServiChaud = dessert.estServiChaud,)
        return dessertRepository.save(dessert)
    }
    /**
     * Récupère la liste complète des desserts enregistrés.
     * @return Liste de tous les desserts.
     */

    fun getDesserts(): List<Dessert> = dessertRepository.findAll()
    /**
     * Recherche un dessert par son identifiant.
     * @throws NoSuchElementException si aucun dessert n’existe avec cet ID.
     */
    fun getDesserById(id: Long):Dessert=dessertRepository.findById(id).
    orElseThrow{NoSuchElementException("Dessert avec id=$id non trouvée")}
    /**
     * Met à jour les informations d’un dessert existant.
     * @param id Identifiant du dessert à modifier.
     * @param newdessert Données de mise à jour.
     * @return Le dessert mis à jour.
     */
    fun updateDessert(id:Long,newdessert: DessertRequist):Dessert{
        val existing=dessertRepository.findById(id).orElseThrow { RuntimeException("Dessert non trouvé") }
        existing.nom = newdessert.nom
        existing.prix = newdessert.prix
        existing.description = newdessert.description
        existing.calories=newdessert.calories
        existing.estServiChaud=newdessert.estServiChaud

        return  dessertRepository.save(existing)

    }
    /**
     * Supprime un dessert existant par son identifiant.
     * @param id Identifiant du dessert à supprimer.
     */
    fun deleteDessert(id:Long) = dessertRepository.deleteById(id)



}
