package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Interface [JpaRepository] de Spring Data pour l'entité [Client].
 *
 * Ce repository gère les opérations de base de données (CRUD - Create, Read, Update, Delete)
 * pour les objets [Client]. Spring Data JPA implémentera automatiquement cette
 * interface lors de l'exécution, en fournissant les méthodes de base
 * comme `save()`, `findById()`, `findAll()`, et `delete()`.
 *
 * L'entité gérée est [Client] et son type d'identifiant est [Long].
 *@see Client
 *@see JpaRepository
 */
@Repository
interface ClientRepository : JpaRepository<Client, Long> {


}

