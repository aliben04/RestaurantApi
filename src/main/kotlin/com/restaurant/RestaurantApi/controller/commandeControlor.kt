package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.Commande
import com.restaurant.RestaurantApi.model.DTO.CommandeRequest
import com.restaurant.RestaurantApi.service.CommandeService
import org.springframework.web.bind.annotation.*
/**
 * Contrôleur REST pour la gestion des [Commande].
 *
 * Expose les endpoints HTTP pour créer, lire, mettre à jour et supprimer
 * des commandes. Tous les endpoints retournent des données au format JSON.
 *
 * **Base URL** : `/api/commandes`
 *
 * **Codes de statut HTTP utilisés :**
 * - 200 OK : Requête réussie
 * - 201 Created : Ressource créée avec succès
 * - 204 No Content : Suppression réussie
 * - 400 Bad Request : Données invalides
 * - 404 Not Found : Ressource non trouvée (ex: Client ou Élément)
 * - 500 Internal Server Error : Erreur serveur
 *
 * @property commandeService Le service contenant la logique métier des commandes.
 * @constructor Crée un nouveau contrôleur avec injection du service.
 */
@RestController
@RequestMapping("/api/commandes")
class CommandeController(
    private val commandeService: CommandeService
) {
    /**
     * Récupère la liste complète de toutes les commandes.
     *
     * **Méthode HTTP** : GET
     * **URL** : `/api/commandes`
     * **Authentification** : Non requise
     *
     * @return ResponseEntity contenant la liste de toutes les
     * commandes et code 200.
     *
     * **Exemple de requête :**
     * ```
     * GET http://localhost:8080/api/commandes
     * ```
     *
     * **Exemple de réponse (200 OK) :**
     * ```json
     * [
     * {
     * "id": 1,
     * "date": "2025-11-05T10:30:00.000+00:00",
     * "elements": [
     * {
     * "id": 2,
     * "type_element": "PLAT",
     * "nom": "Steak Frites",
     * ...
     * },
     * {
     * "id": 5,
     * "type_element": "BOISSON",
     * "nom": "Coca-Cola",
     * ...
     * }
     * ]
     * }
     * ]
     * ```
     */
    @GetMapping
    fun getAllCommandes(): List<Commande> =
        commandeService.getAllCommandes()

    /**
     * Récupère une commande spécifique par son identifiant.
     *
     * **Méthode HTTP** : GET
     * **URL** : `/api/commandes/{id}`
     * **Authentification** : Non requise
     *
     * @param id L'identifiant de la commande à récupérer (dans l'URL)
     * @return ResponseEntity contenant la commande trouvée et code 200.
     * @throws RuntimeException Si la commande n'existe pas
     * (retourne 404 via l'exception handler).
     *
     * **Exemple de requête :**
     * ```
     * GET http://localhost:8080/api/commandes/1
     * ```
     *
     * **Réponse en cas de succès (200 OK) :**
     * ```json
     * {
     * "id": 1,
     * "date": "2025-11-05T10:30:00.000+00:00",
     * "elements": [
     * { "id": 2, "type_element": "PLAT", ... },
     * { "id": 5, "type_element": "BOISSON", ... }
     * ]
     * }
     * ```
     */
    @GetMapping("/{id}")
    fun getCommandeById(@PathVariable id: Long): Commande =
        commandeService.getCommandeById(id)
    /**
     * Crée une nouvelle commande.
     *
     * **Méthode HTTP** : POST
     * **URL** : `/api/commandes`
     * **Content-Type** : application/json
     * **Authentification** : Non requise
     *
     * @param request Le DTO [CommandeRequest] (dans le corps JSON) contenant
     * l'ID du client et la liste des IDs d'éléments.
     * @return ResponseEntity contenant la commande créée et code 201.
     * @throws NoSuchElementException Si le client ou un élément n'existe pas
     * (retourne 404).
     *
     * **Exemple de requête :**
     * ```
     * POST http://localhost:8080/api/commandes
     * Content-Type: application/json
     *
     * {
     * "date": "2025-11-05T10:45:00.000+00:00",
     * "clientId": 1,
     * "elementIds": [2, 5]
     * }
     * ```
     *
     * **Réponse en cas de succès (201 Created) :**
     * (Le code retourne déjà 201 grâce à @ResponseStatus)
     * ```json
     * {
     * "id": 2,
     * "date": "2025-11-05T10:45:00.000+00:00",
     * "elements": [ ... ]
     * }
     * ```
     */
    @PostMapping
    fun createCommande(@RequestBody request: CommandeRequest): Commande =
        commandeService.createCommande(request)
    /**
     * Met à jour une commande existante (client et/ou éléments).
     *
     * **Méthode HTTP** : PUT
     * **URL** : `/api/commandes/{id}`
     * **Paramètres (Query Params)** :
     * - `clientId` (Long, optionnel)
     * - `elementIds` (List<Long>, optionnel)
     * **Authentification** : Non requise
     *
     * @param id L'identifiant de la commande à modifier (dans l'URL)
     * @param clientId L'ID du nouveau client (paramètre de requête, optionnel)
     * @param elementIds La nouvelle liste d'IDs d'éléments (paramètre de requête, optionnel)
     * @return ResponseEntity contenant la commande mise à jour et code 200.
     * @throws RuntimeException Si la commande ou le nouveau client n'existe pas
     * (retourne 404).
     *
     * **Exemple de requête :**
     * (Pour changer les éléments de la commande 1 en [3, 4])
     * ```
     * PUT http://localhost:8080/api/commandes/1?elementIds=3,4
     * ```
     * (Pour changer le client de la commande 1 en 2)
     * ```
     * PUT http://localhost:8080/api/commandes/1?clientId=2
     * ```
     */
    @PutMapping("/{id}")
    fun updateCommande(
        @PathVariable id: Long,
        @RequestParam(required = false) clientId: Long?,
        @RequestParam(required = false) elementIds: List<Long>?
    ): Commande =
        commandeService.updateCommande(id, clientId, elementIds)
    /**
     * Supprime définitivement une commande.
     *
     * **Méthode HTTP** : DELETE
     * **URL** : `/api/commandes/{id}`
     * **Authentification** : Non requise
     *
     *  ATTENTION : Cette action est irréversible !
     *
     * @param id L'identifiant de la commande à supprimer (dans l'URL)
     * @return ResponseEntity vide avec code 204 (No Content).
     * @throws org.springframework.dao.EmptyResultDataAccessException Si la commande
     * n'existe pas (retourne 404).
     *
     * **Exemple de requête :**
     * ```
     * DELETE http://localhost:8080/api/commandes/2
     * ```
     *
     * **Réponse en cas de succès :**
     * - Code 204 (No Content)
     * - Corps vide
     */
    @DeleteMapping("/{id}")
    fun deleteCommande(@PathVariable id: Long) =
        commandeService.deleteCommande(id)
}

