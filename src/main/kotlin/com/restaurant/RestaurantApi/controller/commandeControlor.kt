package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.DTO.CommandeRequest
import com.restaurant.RestaurantApi.model.DTO.CommandeResponse
import com.restaurant.RestaurantApi.service.CommandeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/commandes")
class CommandeController(
    private val commandeService: CommandeService
) {

    // 🔹 GET — afficher toutes les commandes
    @GetMapping
    fun getAllCommandes(): List<CommandeResponse> =
        commandeService.getAllCommandes()

    // 🔹 GET — afficher une commande par ID
    @GetMapping("/{id}")
    fun getCommandeById(@PathVariable id: Long): CommandeResponse =
        commandeService.getCommandeById(id)

    // 🔹 POST — créer une commande
    @PostMapping
    fun createCommande(@RequestBody request: CommandeRequest): CommandeResponse =
        commandeService.createCommande(request)

    // 🔹 PUT — mettre à jour une commande (client et/ou éléments)
    fun updateCommande(
        @PathVariable id: Long,
        @RequestParam(required = false) clientId: Long?,
        @RequestParam(required = false) elementIds: List<Long>?
    ): CommandeResponse =
        commandeService.updateCommande(id, clientId, elementIds)

    // 🔹 DELETE — supprimer une commande
    @DeleteMapping("/{id}")
    fun deleteCommande(@PathVariable id: Long) =
        commandeService.deleteCommande(id)
}

