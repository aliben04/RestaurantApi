package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.ElementMenu
import com.restaurant.RestaurantApi.repository.ElementMenuRepository
import com.restaurant.RestaurantApi.model.DTO.ElementMenuRequist
/**
 * Service de gestion des éléments du menu.
 *
 * Cette classe fournit les opérations CRUD (POST, GET, PUT, DELETE)
 * pour l'entité mère. Étant donné la stratégie d'héritage SINGLE_TABLE, ce service
 * est capable de gérer toutes les entités filles ([PlatPrincipal], [Boisson], [Dessert])
 * de manière générique.
 *
 * @property elementMenuRepository Le repository utilisé pour l'accès aux données.
 * @constructor Initialise le service avec un [ElementMenuRepository].
 * @see ElementMenu
 * @see ElementMenuRequist
 */

@Service
class ElementMenuService(private val elementMenuRepository: ElementMenuRepository) {


    /**
     * Récupère la liste de tous les éléments du menu enregistrés.
     * Cette liste inclut les Plats, Desserts et Boissons.
     *
     * @return La liste complète des [ElementMenu].
     */
    fun getAllElements(): List<ElementMenu> = elementMenuRepository.findAll()

    /**
     * Récupère un élément du menu par son identifiant.
     *
     * @param id L'identifiant Long de l'élément recherché.
     * @return L'[ElementMenu] correspondant.
     * @throws RuntimeException Si aucun élément n'est trouvé avec l'ID spécifié.
     */
    fun getElementById(id: Long): ElementMenu =
        elementMenuRepository.findById(id).orElseThrow { RuntimeException("Élément non trouvé") }

    /**
     * Enregistre un nouvel élément du menu à partir d’une requête DTO.
     *
     * @param request Le [ElementMenuRequist] contenant les données du nouvel élément.
     * @return L'[ElementMenu] sauvegardé avec son ID généré.
     */
    fun saveElement(request: ElementMenuRequist): ElementMenu {
        val elementMenu = ElementMenu(nom = request.nom, prix = request.prix, description = request.description)
        val saved = elementMenuRepository.save(elementMenu)
        return saved
    }
    /**
     * Met à jour un élément existant dans le menu.
     *
     * @param id Identifiant de l’élément à modifier.
     * @param updated Objet [ElementMenuRequist] contenant les nouvelles données.
     * @return L’[ElementMenu] mis à jour.
     */
    fun updateElement(id: Long, updated: ElementMenuRequist): ElementMenu {
        val existing = getElementById(id)
        existing.nom = updated.nom
        existing.prix = updated.prix
        existing.description = updated.description
        return elementMenuRepository.save(existing)
    }
    /**
     * Supprime un élément du menu à partir de son identifiant.
     *
     * @param id Identifiant de l’élément à supprimer.
     */
    fun deleteElement(id: Long) = elementMenuRepository.deleteById(id)

}
