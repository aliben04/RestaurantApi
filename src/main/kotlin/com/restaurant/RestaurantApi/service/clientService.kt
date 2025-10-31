package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.Client
import com.restaurant.RestaurantApi.repository.ClientRepository
import com.restaurant.RestaurantApi.model.DTO.ClientDTO
import com.restaurant.RestaurantApi.model.DTO.ClientRequestDTO

@Service
class ClientService(private val clientRepository: ClientRepository) {

    fun getAllClients(): List<ClientDTO> = clientRepository.findAll().map{ it.toDTO() }

     fun getClientById(id: Long): ClientDTO  =
        clientRepository.findById(id).orElseThrow { RuntimeException("Client non trouvé") }.toDTO()

    fun saveClient(request: ClientRequestDTO): ClientDTO {
        val client = Client(nom = request.nom)
        return clientRepository.save(client).toDTO()
    }

    fun updateClient(id: Long,request: ClientRequestDTO): ClientDTO  {
        val existing = clientRepository.findById(id)
            .orElseThrow { RuntimeException("Client non trouvé") }
        existing.nom=request.nom
        val updated = clientRepository.save(existing)
        return updated.toDTO()
    }

    fun deleteClient(id: Long) = clientRepository.deleteById(id)

    private fun Client.toDTO(): ClientDTO = ClientDTO(
        id = this.id,
        nom = this.nom
    )
}
