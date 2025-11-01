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

    @GetMapping
    fun getAllCommandes(): List<CommandeResponse> =
        commandeService.getAllCommandes()


    @GetMapping("/{id}")
    fun getCommandeById(@PathVariable id: Long): CommandeResponse =
        commandeService.getCommandeById(id)

    @PostMapping
    fun createCommande(@RequestBody request: CommandeRequest): CommandeResponse =
        commandeService.createCommande(request)


    fun updateCommande(
        @PathVariable id: Long,
        @RequestParam(required = false) clientId: Long?,
        @RequestParam(required = false) elementIds: List<Long>?
    ): CommandeResponse =
        commandeService.updateCommande(id, clientId, elementIds)

    @DeleteMapping("/{id}")
    fun deleteCommande(@PathVariable id: Long) =
        commandeService.deleteCommande(id)
}

