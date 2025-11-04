package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.model.Client
import com.restaurant.RestaurantApi.service.ClientService
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.ClientRequestDTO
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/clients")
class ClientController(private val clientService: ClientService) {

    @GetMapping
    fun getAllClients(): List<Client> = clientService.getAllClients()

    @GetMapping("/{id}")
    //?id=Valeu
    fun getClientById(@PathVariable  id: Long): Client =
        clientService.getClientById(id)

    @PostMapping
    fun createClient(@Valid @RequestBody request: ClientRequestDTO): Client = clientService.saveClient(request)

    @PutMapping("/{id}")
    fun updateClient(@PathVariable id: Long,@Valid @RequestBody equest: ClientRequestDTO): Client =
        clientService.updateClient(id, equest)

    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Long) = clientService.deleteClient(id)
}