package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.Commande
import com.restaurant.RestaurantApi.model.DTO.CommandeRequest
import com.restaurant.RestaurantApi.service.CommandeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/commandes")
class CommandeController(
    private val commandeService: CommandeService
) {

    @GetMapping
    fun getAllCommandes(): List<Commande> =
        commandeService.getAllCommandes()


    @GetMapping("/{id}")
    fun getCommandeById(@PathVariable id: Long): Commande =
        commandeService.getCommandeById(id)

    @PostMapping
    fun createCommande(@RequestBody request: CommandeRequest): Commande =
        commandeService.createCommande(request)


    fun updateCommande(
        @PathVariable id: Long,
        @RequestParam(required = false) clientId: Long?,
        @RequestParam(required = false) elementIds: List<Long>?
    ): Commande =
        commandeService.updateCommande(id, clientId, elementIds)

    @DeleteMapping("/{id}")
    fun deleteCommande(@PathVariable id: Long) =
        commandeService.deleteCommande(id)
}

