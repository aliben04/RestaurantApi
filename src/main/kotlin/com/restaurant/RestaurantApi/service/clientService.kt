package com.restaurant.RestaurantApi.service

import org.springframework.stereotype.Service
import com.restaurant.RestaurantApi.model.Client
import com.restaurant.RestaurantApi.repository.ClientRepository
import com.restaurant.RestaurantApi.model.DTO.ClientRequestDTO

@Service
class ClientService(private val clientRepository: ClientRepository) {

    fun getAllClients(): List<Client> = clientRepository.findAll()

     fun getClientById(id: Long): Client  =
        clientRepository.findById(id).orElseThrow { RuntimeException("Client non trouvé") }

    fun saveClient(request: ClientRequestDTO): Client {
        val client = Client(nom = request.nom)
        return clientRepository.save(client)
    }



    fun updateClient(id: Long,request: ClientRequestDTO): Client  {
        val existing = clientRepository.findById(id)
            .orElseThrow { RuntimeException("Client non trouvé") }
        existing.nom=request.nom
        val updated = clientRepository.save(existing)
        return updated
    }

    fun deleteClient(id: Long) = clientRepository.deleteById(id)

//    private fun Client.toDTO(): ClientDTO = ClientDTO(
//        id = this.id,
//        nom = this.nom
//    )
}
