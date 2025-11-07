package com.restaurant.RestaurantApi.service

import com.restaurant.RestaurantApi.model.Boisson
import com.restaurant.RestaurantApi.model.DTO.BoissonRequest
import com.restaurant.RestaurantApi.repository.BoissonRepository
import org.springframework.stereotype.Service
/**
 * Service de gestion des boissons.
 *
 * Cette classe contient la logique métier associée aux entités [Boisson],
 * en utilisant le [BoissonRepository] pour interagir avec la base de données.
 *
 * Fournit des méthodes pour :(POST,GET,PUT,DELETE)
 *
 * @constructor Initialise le service avec une instance de [BoissonRepository].
 * @param boissonRepository Repository JPA pour accéder aux données des boissons.
 */
@Service
class BoissonService(private val boissonRepository: BoissonRepository) {
    /**
     * Enregistre une nouvelle boisson dans la base de données.
     *
     * @param request Objet [BoissonRequest] contenant les données de la nouvelle boisson.
     * @return La [Boisson] sauvegardée avec son identifiant généré.
     */
    fun saveBoisson(request: BoissonRequest): Boisson{
        val boisson = Boisson(nom = request.nom, prix = request.prix, description = request.description, volumeLitre = request.volumeLitre, contientAlcool = request.contientAlcool)
        return boissonRepository.save(boisson)
    }

    /**
     * Récupère toutes les boissons enregistrées.
     *
     * @return Une liste de toutes les [Boisson] disponibles dans la base de données.
     */
    fun getAllBoissons(): List<Boisson> = boissonRepository.findAll()
    /**
     * Recherche une boisson à partir de son identifiant.
     *
     * @param id Identifiant de la boisson recherchée.
     * @return La [Boisson] correspondante.
     * @throws NoSuchElementException Si aucune boisson avec cet identifiant n’existe.
     */
    fun getBoissonById(id: Long): Boisson =
        boissonRepository.findById(id).orElseThrow { NoSuchElementException("Boisson avec id=$id non trouvée") }
    /**
     * Met à jour les informations d'une boisson existante.
     *
     * @param id Identifiant de la boisson à modifier.
     * @param request Données mises à jour contenues dans un objet [BoissonRequest].
     * @return La [Boisson] mise à jour.
     * @throws NoSuchElementException Si la boisson n’existe pas.
     */
    fun updateBoisson(id: Long,request: BoissonRequest): Boisson {
        val existing = getBoissonById(id)
        existing.nom = request.nom
        existing.prix = request.prix
        existing.description = request.description
        existing.volumeLitre=request.volumeLitre
        existing.contientAlcool=request.contientAlcool

        return boissonRepository.save(existing)

    }
/**
 * Supprime une boisson existante par son identifiant.
 *
 * @param id Identifiant de la boisson à supprimer.
 */
    fun deleteBoisson(id: Long) = boissonRepository.deleteById(id)

}

