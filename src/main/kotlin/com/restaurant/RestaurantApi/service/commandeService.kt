package com.restaurant.RestaurantApi.service

import com.restaurant.RestaurantApi.model.Commande
import com.restaurant.RestaurantApi.model.DTO.CommandeRequest
import com.restaurant.RestaurantApi.model.DTO.CommandeResponse
import com.restaurant.RestaurantApi.model.DTO.ElementMenuDTO
import com.restaurant.RestaurantApi.repository.CommandeRepository
import com.restaurant.RestaurantApi.repository.ClientRepository
import com.restaurant.RestaurantApi.repository.ElementMenuRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommandeService(
    private val commandeRepository: CommandeRepository,
    private val clientRepository: ClientRepository,
    private val elementMenuRepository: ElementMenuRepository
) {

    fun getAllCommandes(): List<CommandeResponse> = commandeRepository.findAll().map { it.toResponse() }

    fun getCommandeById(id: Long): CommandeResponse = commandeRepository.findById(id).orElseThrow { RuntimeException("Commande non trouvée") }.toResponse()

    fun createCommande(request: CommandeRequest): CommandeResponse {
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

        val saved = commandeRepository.save(newCommande)
        return saved.toResponse()
    }

    fun updateCommande(id: Long, clientId: Long?, elementIds: List<Long>?): CommandeResponse {
        val commande = commandeRepository.findById(id)
            .orElseThrow { RuntimeException("Commande non trouvée") }

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
        return updated.toResponse()
    }

    fun deleteCommande(id: Long) {
        commandeRepository.deleteById(id)
    }
    private fun Commande.toResponse(): CommandeResponse = CommandeResponse(
        id = this.id,
        date = this.date,
        clientName = this.client.nom,
        elements = this.elements.map {
            ElementMenuDTO(
                id = it.id,
                nom = it.nom,
                prix = it.prix,
                description = it.description
            )
        }
    )
}
