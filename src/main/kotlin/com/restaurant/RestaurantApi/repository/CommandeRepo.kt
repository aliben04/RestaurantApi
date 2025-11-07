package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Commande
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date
@Repository
/**
 * Interface [JpaRepository] de Spring Data pour l'entité [Commande].
 *
 * Ce repository gère les opérations de base de données (CRUD - Create, Read, Update, Delete)
 * pour les objets [Commande]. Spring Data JPA implémentera automatiquement cette
 * interface lors de l'exécution.
 *
 * L'entité gérée est [Commande] et son type d'identifiant est [Long].
 *
 *@see Commande
 *@see JpaRepository
 */
interface CommandeRepository : JpaRepository<Commande, Long> {


}