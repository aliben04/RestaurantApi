package com.restaurant.RestaurantApi.service

import com.restaurant.RestaurantApi.model.Commande
import com.restaurant.RestaurantApi.model.DTO.CommandeRequest
import com.restaurant.RestaurantApi.repository.CommandeRepository
import com.restaurant.RestaurantApi.repository.ClientRepository
import com.restaurant.RestaurantApi.repository.ElementMenuRepository
import org.springframework.stereotype.Service
import java.util.*
/**
 * Service gérant la logique métier complexe pour les [Commande].
 *
 * Ce service coordonne les interactions entre [CommandeRepository],
 * [ClientRepository], et [ElementMenuRepository] pour créer,
 * lire, mettre à jour et supprimer des commandes.
 *
 * @property commandeRepository Le repository pour l'accès aux données des [Commande].
 * @property clientRepository Le repository pour trouver les [Client] associés.
 * @property elementMenuRepository Le repository pour trouver les [ElementMenu] associés.
 * @constructor Crée une instance du service avec injection des dépendances.
 */
@Service
class CommandeService(
    private val commandeRepository: CommandeRepository,
    private val clientRepository: ClientRepository,
    private val elementMenuRepository: ElementMenuRepository
) {
    /**
     * Récupère la liste complète de toutes les commandes.
     *
     * @return Une [List] de [Commande].
     */
    fun getAllCommandes(): List<Commande> = commandeRepository.findAll()
    /**
     * Récupère une commande spécifique par son identifiant.
     *
     * @param id L'identifiant unique (Long) de la commande à rechercher.
     * @return L'entité [Commande] correspondante.
     * @throws RuntimeException Si aucune commande n'est trouvée avec cet ID.
     */
    fun getCommandeById(id: Long): Commande = commandeRepository.findById(id).orElseThrow { RuntimeException("Commande non trouvée") }
    /**
     * Crée et sauvegarde une nouvelle commande.
     *
     * Vérifie l'existence du client et de tous les éléments de menu
     * avant de créer la commande.
     *
     * @param request Le DTO [CommandeRequest] contenant l'ID du client et les IDs des éléments.
     * @return L'entité [Commande] sauvegardée.
     * @throws NoSuchElementException Si le client ou l'un des éléments de menu n'existe pas.
     *
     * Exemple d'utilisation :
     * ```
     * val nouvelleCmd = CommandeRequest(date = Date(), clientId = 1L, elementIds = listOf(2L, 5L))
     * try {
     * val commandeCreee = commandeService.createCommande(nouvelleCmd)
     * println(commandeCreee.id) // Affiche l'ID de la nouvelle commande
     * } catch (e: NoSuchElementException) {
     * println(e.message) // Affiche "Client... n'a pas été trouvé" ou "Les éléments... sont introuvables"
     * }
     * ```
     */
    fun createCommande(request: CommandeRequest): Commande {
        val client = clientRepository.findById(request.clientId).orElseThrow { NoSuchElementException("Client avec ID ${request.clientId} n'a pas été trouvé.") }

        val existingElements = elementMenuRepository.findAllById(request.elementIds)

        if (existingElements.size != request.elementIds.size) {
            val foundIds = existingElements.map { it.id }
            val missingIds = request.elementIds.filter { it !in foundIds }
            throw NoSuchElementException("Les éléments de menu avec IDs suivants sont introuvables: $missingIds")
        }

        val newCommande = Commande(
            date = request.date,
            client = client,
            elements = existingElements.toMutableList()
        )

        return commandeRepository.save(newCommande)
    }
    /**
     * Met à jour une commande existante.
     *
     * Permet de changer le client ou la liste des éléments d'une commande.
     * La date de la commande est mise à jour à l'heure actuelle lors de la modification.
     *
     * @param id L'identifiant de la commande à modifier.
     * @param clientId L'ID (nullable) du nouveau client. Si null, le client n'est pas changé.
     * @param elementIds La liste (nullable) des nouveaux IDs d'éléments. Si null, les éléments ne sont pas changés.
     * @return La [Commande] mise à jour.
     * @throws RuntimeException Si la commande elle-même ou le nouveau client n'est pas trouvé.
     *
     * Exemple d'utilisation :
     * ```
     * val idExistant: Long = 3
     * val nouveauxElements = listOf(1L, 7L, 8L) // On veut changer les plats
     *
     * try {
     * // On passe 'null' pour le clientId pour ne pas le changer
     * val cmdMiseAJour = commandeService.updateCommande(idExistant, null, nouveauxElements)
     * println(cmdMiseAJour.elements.size) // Affiche 3
     * println(cmdMiseAJour.date) // Affiche la date actuelle
     * } catch (e: RuntimeException) {
     * println("La commande 3 ou le client n'existe pas.")
     * }
     * ```
     */
    fun updateCommande(id: Long, clientId: Long?, elementIds: List<Long>?): Commande {
        val commande = getCommandeById(id)

        if (clientId != null) {
            val newClient = clientRepository.findById(clientId)
                .orElseThrow { RuntimeException("Client non trouvé") }
            commande.client = newClient
        }

        if (elementIds != null) {
            val newElements = elementMenuRepository.findAllById(elementIds)
            commande.elements.clear()
            commande.elements.addAll(newElements)
        }

        commande.date = Date()
        val updated = commandeRepository.save(commande)
        return updated
    }
    /**
     * Supprime une commande par son identifiant.
     *
     * Note : Lèvera une [org.springframework.dao.EmptyResultDataAccessException]
     * si l'identifiant n'est pas trouvé.
     *
     * @param id L'identifiant de la commande à supprimer.
     *
     * Exemple d'utilisation :
     * ```
     * val idASupprimer: Long = 10
     * try {
     * commandeService.deleteCommande(idASupprimer)
     * println("Commande 10 supprimée.")
     * } catch (e: org.springframework.dao.EmptyResultDataAccessException) {
     * println("La commande 10 n'existe pas.")
     * }
     * ```
     */
    fun deleteCommande(id: Long) {
        commandeRepository.deleteById(id)
    }

}
