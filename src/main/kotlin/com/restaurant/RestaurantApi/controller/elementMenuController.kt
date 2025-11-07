package com.restaurant.RestaurantApi.controller



import com.restaurant.RestaurantApi.service.ElementMenuService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.ElementMenuRequist
import com.restaurant.RestaurantApi.model.ElementMenu

/**
 * Contrôleur REST pour la gestion des [ElementMenu].
 *
 * Expose les endpoints HTTP pour effectuer les opérations CRUD
 * sur les éléments de menu (Plats, Boissons, Desserts, etc.).
 * Tous les endpoints retournent des données au format JSON.
 *
 * **Base URL** : `/api/elements`
 *
 * **Codes de statut HTTP utilisés :**
 * - 200 OK : Requête réussie
 * - 201 Created : Ressource créée avec succès
 * - 204 No Content : Suppression réussie
 * - 400 Bad Request : Données invalides (validation échouée)
 * - 404 Not Found : Ressource non trouvée
 * - 500 Internal Server Error : Erreur serveur
 *
 * @property elementMenuService Le service contenant la logique métier
 * @constructor Crée un nouveau contrôleur avec injection du service
 */
@RestController
@RequestMapping("/api/elements")
class ElementMenuController(private val elementMenuService: ElementMenuService) {

    /**
     * Récupère tous les éléments du menu.
     *
     * @return Une liste de tous les [ElementMenu] disponibles.
     *
     * Exemple de requête :
     * ```
     * GET : /api/elements
     * ```
     */
    @GetMapping
    fun getAllElements(): List<ElementMenu> = elementMenuService.getAllElements()
    /**
     * Récupère un élément du menu par son id.
     *
     * @param id L'identifiant unique de l’élément à rechercher.
     * @return L’[ElementMenu] correspondant à l’identifiant donné.
     * @throws RuntimeException Si aucun élément ne correspond à l’ID fourni.
     *
     * Exemple de requête :
     * ```
     * GET :  /api/elements/1
     * ```
     */
    @GetMapping("/{id}")
    fun getElementById(@PathVariable id: Long): ElementMenu = elementMenuService.getElementById(id)


    /**
     * Crée un nouvel élément du menu.
     *
     * @param element Objet [ElementMenuRequist] contenant les informations de l’élément à créer.
     * @return L’[ElementMenu] créé, incluant son identifiant généré.
     *
     * Exemple de requête :
     * ```
     * POST /api/elements
     * Content-Type: application/json
     *
     * {
     *   "nom": "Pizza Margherita",
     *   "prix": 12.5,
     *   "description": "Pizza italienne classique"
     * }
     * ```
     */
    @PostMapping
    fun createElement(@Valid @RequestBody element: ElementMenuRequist): ElementMenu = elementMenuService.saveElement(element)
    /**
     * Met à jour un élément du menu existant.
     *
     * @param id Identifiant de l’élément à modifier.
     * @param updated Objet [ElementMenuRequist] contenant les nouvelles données.
     * @return L’[ElementMenu] mis à jour.
     * @throws RuntimeException Si l’élément n’existe pas.
     *
     * Exemple de requête :
     * ```
     * PUT /api/elements/2
     * Content-Type: application/json
     *
     * {
     *   "nom": "Burger végétarien",
     *   "prix": 10.0,
     *   "description": "Burger sans viande avec légumes grillés"
     * }
     * ```
     */
    @PutMapping("/{id}")
    fun updateElement( @PathVariable id: Long,@Valid @RequestBody updated: ElementMenuRequist): ElementMenu =
        elementMenuService.updateElement(id, updated)
    /**
     * Supprime un élément du menu à partir de son identifiant.
     *
     * @param id Identifiant de l’élément à supprimer.
     *
     * Exemple de requête :
     * ```
     * DELETE /api/elements/3
     * ```
     */
    @DeleteMapping("/{id}")
    fun deleteElement(@PathVariable id: Long) = elementMenuService.deleteElement(id)
}