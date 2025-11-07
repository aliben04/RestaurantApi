package com.restaurant.RestaurantApi.controller
import com.restaurant.RestaurantApi.service.PlatPrincipalService
import com.restaurant.RestaurantApi.model.DTO.*
import com.restaurant.RestaurantApi.model.Dessert
import com.restaurant.RestaurantApi.model.PlatPrincipal
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
/**
 * Contrôleur REST pour gérer les **plats principaux**.
 *
 * Routes exposées sous `/api/plats` :
 * - `GET /api/plats` : liste tous les plats
 * - `GET /api/plats/{id}` : récupère un plat par ID
 * - `POST /api/plats` : crée un nouveau plat
 * - `PUT /api/plats/{id}` : met à jour un plat existant
 * - `DELETE /api/plats/{id}` : supprime un plat
 *
 * @property platPrincipalService Service métier pour les plats principaux.
 */
@RestController
@RequestMapping("/api/plats")
class PlatprincipalControllor(val platPrincipalService: PlatPrincipalService) {
    /**
     * Récupère la liste complète des plat enregistrés.
     * @return Liste d’objets [PlatPrincipal].
     */
    @GetMapping
    fun getAllplats():List<PlatPrincipal> = platPrincipalService.getPlatsprincipal()
    /**
     * Récupère un plat par son identifiant.
     * @param id Identifiant du plat à rechercher.
     * @return Le plat correspondant à l’ID.
     * @throws NoSuchElementException si aucun dessert n’est trouvé.
     */
    @GetMapping("/{id}")
    fun getPlatsbyId(@PathVariable id: Long): PlatPrincipal=platPrincipalService.getplatById(id)
    /**
     * Crée un nouveau plat à partir des données reçues dans la requête HTTP.
     * @param plat Données validées du plat ([platPrincipalRequist]).
     * @return Le plat enregistré avec son identifiant généré.
     */
    @PostMapping
    fun savePlats(@Valid @RequestBody plat: platPrincipalRequist): PlatPrincipal=platPrincipalService.savePlatPrincipal(plat)
    /**
     * Met à jour un plat existant identifié par son ID.
     * @param id Identifiant du plat à modifier.
     * @param newPlat Nouvelles données validées du dessert.
     * @return Le plat mis à jour.
     */
    @PutMapping("/{id}")
    fun updatePlat( @PathVariable id : Long,@Valid @RequestBody newPlat: platPrincipalRequist): PlatPrincipal=platPrincipalService.uppdatePlatPrincipal(id,newPlat)
    /**
     * Supprime un plat existant par son identifiant.
     * @param id Identifiant du plat à supprimer.
     */
    @DeleteMapping("/{id}")
    fun deletePlat(@PathVariable id:Long)=platPrincipalService.deletePlatPrincipal(id)
}