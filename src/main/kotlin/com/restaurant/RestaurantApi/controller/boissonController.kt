package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.Boisson
import com.restaurant.RestaurantApi.service.BoissonService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.BoissonRequest
/**
 * Contrôleur REST pour la gestion des **boissons** dans le menu du restaurant.
 *
 * Cette classe expose les endpoints permettant d'effectuer les opérations CRUD :
 * - **Créer** une nouvelle boisson (`POST`)
 * - **Lire** toutes les boissons ou une boisson spécifique (`GET`)
 * - **Mettre à jour** une boisson existante (`PUT`)
 * - **Supprimer** une boisson (`DELETE`)
 *
 * Toutes les opérations de logique métier sont déléguées au service [BoissonService].
 *
 * @constructor Injecte une instance de [BoissonService].
 * @see BoissonService
 * @see com.restaurant.RestaurantApi.model.Boisson
 * @see com.restaurant.RestaurantApi.model.DTO.BoissonRequest
 */
@RestController
@RequestMapping("/api/boissons")
class BoissonControllor(private val boissonService: BoissonService) {

    /**
     * Crée une nouvelle boisson dans la base de données.
     *
     * @param boisson Objet [BoissonRequest] contenant les informations de la nouvelle boisson.
     * @return La [Boisson] créée, incluant son identifiant généré.
     *
     * ### Exemple de requête :
     * ```http
     * POST /api/boissons
     * Content-Type: application/json
     *
     * {
     *   "nom": "Jus de Pomme",
     *   "prix": 4.0,
     *   "description": "Jus de pomme bio pressé à froid",
     *   "volumeLitre": 0.25,
     *   "contientAlcool": false
     * }
     * ```
     */
    @PostMapping
    fun saveBoisson(@Valid @RequestBody boisson: BoissonRequest): Boisson =
        boissonService.saveBoisson(boisson)

    /**
     * Récupère la liste de toutes les boissons disponibles.
     *
     * @return Une liste de toutes les [Boisson] enregistrées.
     *
     * ### Exemple :
     * ```http
     * GET /api/boissons
     * ```
     *
     * ### Exemple de réponse :
     * ```json
     * [
     *   {
     *     "id": 1,
     *     "nom": "Coca-Cola",
     *     "prix": 3.5,
     *     "description": "Soda gazeux rafraîchissant",
     *     "volumeLitre": 0.33,
     *     "contientAlcool": false
     *   },
     *   {
     *     "id": 2,
     *     "nom": "Bière Artisanale",
     *     "prix": 5.0,
     *     "description": "Bière blonde locale",
     *     "volumeLitre": 0.5,
     *     "contientAlcool": true
     *   }
     * ]
     * ```
     */
    @GetMapping
    fun getAllBoissons(): List<Boisson> = boissonService.getAllBoissons()

    /**
     * Récupère une boisson spécifique par son identifiant.
     *
     * @param id Identifiant unique de la boisson.
     * @return L’objet [Boisson] correspondant.
     * @throws NoSuchElementException Si aucune boisson n’existe avec cet identifiant.
     *
     * ### Exemple :
     * ```http
     * GET /api/boissons/1
     * ```
     */
    @GetMapping("/{id}")
    fun getBoissonById(@PathVariable id: Long): Boisson =
        boissonService.getBoissonById(id)

    /**
     * Met à jour une boisson existante.
     *
     * @param id Identifiant de la boisson à modifier.
     * @param newBoisson Objet [BoissonRequest] contenant les nouvelles données.
     * @return La [Boisson] mise à jour.
     * @throws NoSuchElementException Si la boisson n’existe pas.
     *
     * ### Exemple de requête :
     * ```http
     * PUT /api/boissons/2
     * Content-Type: application/json
     *
     * {
     *   "nom": "Thé Glacé Citron",
     *   "prix": 3.0,
     *   "description": "Thé glacé rafraîchissant au citron",
     *   "volumeLitre": 0.5,
     *   "contientAlcool": false
     * }
     * ```
     */
    @PutMapping("/{id}")
    fun updateBoisson(
        @PathVariable id: Long,
        @Valid @RequestBody newBoisson: BoissonRequest
    ): Boisson = boissonService.updateBoisson(id, newBoisson)

    /**
     * Supprime une boisson de la base de données.
     *
     * @param id Identifiant de la boisson à supprimer.
     *
     * ### Exemple :
     * ```http
     * DELETE /api/boissons/3
     * ```
     */
    @DeleteMapping("/{id}")
    fun deleteBoisson(@PathVariable id: Long) =
        boissonService.deleteBoisson(id)
}

