package com.restaurant.RestaurantApi.controller

import com.restaurant.RestaurantApi.model.DTO.DessertRequist
import com.restaurant.RestaurantApi.model.Dessert
import com.restaurant.RestaurantApi.service.DessertService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

/**
 * Contrôleur REST gérant les opérations CRUD sur les **desserts**.
 *
 * Fournit les endpoints de l’API `/api/desserts` permettant :
 * - La création de nouveaux desserts
 * - La consultation de la liste des desserts
 * - La récupération d’un dessert par son identifiant
 * - La mise à jour et la suppression de desserts existants
 *
 * Ce contrôleur délègue la logique métier au [DessertService].
 *
 * ### Routes principales :
 * | Méthode | Endpoint | Description |
 * |----------|-----------|-------------|
 * | `GET` | `/api/desserts` | Récupère tous les desserts |
 * | `GET` | `/api/desserts/{id}` | Récupère un dessert par son ID |
 * | `POST` | `/api/desserts` | Crée un nouveau dessert |
 * | `PUT` | `/api/desserts/{id}` | Met à jour un dessert existant |
 * | `DELETE` | `/api/desserts/{id}` | Supprime un dessert |
 *
 * ### Exemple JSON pour la création :
 * ```json
 * {
 *   "nom": "Crème Brûlée",
 *   "prix": 6.0,
 *   "description": "Crème vanillée caramélisée au sucre brun",
 *   "calories": 380,
 *   "estServiChaud": false
 * }
 * ```
 *
 * ### Réponse attendue :
 * ```json
 * {
 *   "id": 1,
 *   "nom": "Crème Brûlée",
 *   "prix": 6.0,
 *   "description": "Crème vanillée caramélisée au sucre brun",
 *   "calories": 380,
 *   "estServiChaud": false
 * }
 * ```
 *
 * @property dessertService Service métier chargé de la gestion des desserts.
 */
@RestController
@RequestMapping("/api/desserts")
class DessertController(private val dessertService: DessertService) {

    /**
     * Récupère la liste complète des desserts enregistrés.
     * @return Liste d’objets [Dessert].
     */
    @GetMapping
    fun getDesserts(): List<Dessert> = dessertService.getDesserts()

    /**
     * Récupère un dessert par son identifiant.
     * @param id Identifiant du dessert à rechercher.
     * @return Le dessert correspondant à l’ID.
     * @throws NoSuchElementException si aucun dessert n’est trouvé.
     */
    @GetMapping("/{id}")
    fun getDessertById(@PathVariable id: Long): Dessert =
        dessertService.getDesserById(id)

    /**
     * Crée un nouveau dessert à partir des données reçues dans la requête HTTP.
     * @param dessert Données validées du dessert ([DessertRequist]).
     * @return Le dessert enregistré avec son identifiant généré.
     */
    @PostMapping
    fun saveDessert(@Valid @RequestBody dessert: DessertRequist): Dessert =
        dessertService.saveDessert(dessert)

    /**
     * Met à jour un dessert existant identifié par son ID.
     * @param id Identifiant du dessert à modifier.
     * @param newDessert Nouvelles données validées du dessert.
     * @return Le dessert mis à jour.
     */
    @PutMapping("/{id}")
    fun updateDessert(
        @PathVariable id: Long,
        @Valid @RequestBody newDessert: DessertRequist
    ): Dessert = dessertService.updateDessert(id, newDessert)

    /**
     * Supprime un dessert existant par son identifiant.
     * @param id Identifiant du dessert à supprimer.
     */
    @DeleteMapping("/{id}")
    fun deleteDessert(@PathVariable id: Long) =
        dessertService.deleteDessert(id)
}