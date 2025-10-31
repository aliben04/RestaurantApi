package com.restaurant.RestaurantApi.controller


import com.restaurant.RestaurantApi.service.ClientService
import org.springframework.web.bind.annotation.*
import com.restaurant.RestaurantApi.model.DTO.ClientDTO
import com.restaurant.RestaurantApi.model.DTO.ClientRequestDTO
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/clients")
class ClientController(private val clientService: ClientService) {

    @GetMapping
    fun getAllClients(): List<ClientDTO> = clientService.getAllClients()

    @GetMapping("/get-Client")
    //?id=Valeu
    fun getClientById(@RequestParam id: Long): ClientDTO =
        clientService.getClientById(id)

    @PostMapping
    fun createClient(@Valid @RequestBody request: ClientRequestDTO): ClientDTO = clientService.saveClient(request)

    @PutMapping("/{id}")
    fun updateClient(@PathVariable id: Long,@Valid @RequestBody equest: ClientRequestDTO): ClientDTO =
        clientService.updateClient(id, equest)

    @DeleteMapping("/{id}")
    fun deleteClient(@PathVariable id: Long) = clientService.deleteClient(id)
}