package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.Client
import com.restaurant.RestaurantApi.service.ClientService
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.ClientRequestDTO
import jakarta.validation.Valid
/**
 * Contrôleur REST pour la gestion des [Client]s.
 *
 * Expose les endpoints HTTP pour effectuer toutes les opérations CRUD
 * sur les Clients. Tous les endpoints retournent des données au format JSON.
 *
 * **Chemin de base** : `/api/clients`
 *
 * **Codes de statut HTTP utilisés :**
 * - 200 OK : Requête réussie
 * - 201 Created : Ressource créée avec succès
 * - 204 No Content : Suppression réussie
 * - 400 Bad Request : Données invalides (échec de validation)
 * - 404 Not Found : Ressource non trouvée
 *
 * @property clientService Le service contenant la logique métier
 */
@RestController
@RequestMapping("/api/clients")
class ClientController(private val clientService: ClientService) {
    /**
     * Récupère la liste complète de tous les clients.
     *
     * **Endpoint:** `GET /api/clients`
     *
     * @return Une [List] de [Client]. Peut être vide si aucun client n'existe.
     *
     * ---
     * **Test Postman :**
     * 1.  **Méthode:** `GET`
     * 2.  **URL:** `http://localhost:8080/api/clients`
     * 3.  **Réponse attendue (200 OK) :**
     * ```json
     * [
     * {
     * "id": 1,
     * "nom": "Hamza",
     * "commandes": []
     * },
     * {
     * "id": 2,
     * "nom": "Ali",
     * "commandes": []
     * }
     * ]
     * ```
     */
    @GetMapping
    fun getAllClients(): List<Client> = clientService.getAllClients()
    /**
     * Récupère un client spécifique par son identifiant.
     *
     * **Endpoint:** `GET /api/clients/{id}`
     *
     * @param id L'identifiant unique (Long) du client à récupérer.
     * @return Le [Client] correspondant.
     * @throws RuntimeException si le client n'est pas trouvé (résulte en HTTP 404).
     *
     * ---
     * **Test Postman (Succès) :**
     * 1.  **Méthode:** `GET`
     * 2.  **URL:** `http://localhost:8080/api/clients/1`
     * 3.  **Réponse attendue (200 OK) :**
     * ```json
     * {
     * "id": 1,
     * "nom": "Hamza",
     * "commandes": []
     * }
     * ```
     *
     * **Test Postman (Erreur 404) :**
     * 1.  **Méthode:** `GET`
     * 2.  **URL:** `http://localhost:8080/api/clients/99`
     * 3.  **Réponse attendue (404 Not Found) :**
     * (Contenu dépend de votre gestionnaire d'exceptions)
     */
    @GetMapping("/{id}")
    //?id=Valeu
    fun getClientById(@PathVariable  id: Long): Client =
        clientService.getClientById(id)
    /**
     * Crée un nouveau client.
     *
     * **Endpoint:** `POST /api/clients`
     *
     * Le corps de la requête doit contenir un [ClientRequestDTO] valide.
     *
     * @param request Le DTO contenant les informations du client à créer.
     * @return Le [Client] nouvellement créé (incluant son ID), avec un statut HTTP 201 (Created).
     *
     * ---
     * **Test Postman (Succès) :**
     * 1.  **Méthode:** `POST`
     * 2.  **URL:** `http://localhost:8080/api/clients`
     * 3.  **Body:** (Onglet "Body" -> "raw" -> "JSON")
     * ```json
     * {
     * "nom": "Nouveau Client"
     * }
     * ```
     * 4.  **Réponse attendue (201 Created) :**
     * ```json
     * {
     * "id": 3,
     * "nom": "Nouveau Client",
     * "commandes": []
     * }
     * ```
     *
     * **Test Postman (Erreur 400 - Validation) :**
     * 1.  **Body:** (raw, JSON)
     * ```json
     * {
     * "nom": "invalid"
     * }
     * ```
     * 2.  **Réponse attendue (400 Bad Request) :**
     * (Message d'erreur de validation)
     */
    @PostMapping
    fun createClient(@Valid @RequestBody request: ClientRequestDTO): Client = clientService.saveClient(request)
    /**
     * Met à jour un client existant.
     *
     * **Endpoint:** `PUT /api/clients/{id}`
     *
     * @param id L'identifiant du client à mettre à jour.
     * @param request Le DTO contenant les nouvelles informations validées.
     * @return Le [Client] mis à jour.
     * @throws RuntimeException si le client n'est pas trouvé (résulte en HTTP 404).
     *
     * ---
     * **Test Postman (Succès) :**
     * 1.  **Méthode:** `PUT`
     * 2.  **URL:** `http://localhost:8080/api/clients/1`
     * 3.  **Body:** (Onglet "Body" -> "raw" -> "JSON")
     * ```json
     * {
     * "nom": "Client Mis à Jour"
     * }
     * ```
     * 4.  **Réponse attendue (200 OK) :**
     * ```json
     * {
     * "id": 1,
     * "nom": "Client Mis à Jour",
     * "commandes": []
     * }
     * ```
     *
     * **Test Postman (Erreur 404) :**
     * 1.  **Méthode:** `PUT`
     * 2.  **URL:** `http://localhost:8080/api/clients/99`
     * 3.  **Body:** (raw, JSON) `{"nom": "Test"}`
     * 4.  **Réponse attendue (404 Not Found) :**
     * (Contenu dépend de votre gestionnaire d'exceptions)
     */
    @PutMapping("/{id}")
    fun updateClient(@PathVariable id: Long,@Valid @RequestBody equest: ClientRequestDTO): Client =
        clientService.updateClient(id, equest)
    /**
     * Supprime un client par son identifiant.
     *
     * **Endpoint:** `DELETE /api/clients/{id}`
     *
     * Retourne une réponse HTTP 204 (No Content) en cas de succès.
     *
     * @param id L'identifiant du client à supprimer.
     *
     * ---
     * **Test Postman :**
     * 1.  **Méthode:** `DELETE`
     * 2.  **URL:** `http://localhost:8080/api/clients/1`
     * 3.  **Réponse attendue (204 No Content) :**
     * (Pas de contenu / "No Content" dans le statut)
     */
    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Long) = clientService.deleteClient(id)
}