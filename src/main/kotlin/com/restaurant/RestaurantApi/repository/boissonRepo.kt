package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Boisson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
/**
 * Repository JPA pour la gestion des entités [Boisson].
 *
 * Cette interface hérite de [JpaRepository] et fournit automatiquement
 * toutes les opérations CRUD standards (Create, Read, Update, Delete).
 *
 * Elle interagit directement avec la base de données sans nécessiter
 * d’implémentation explicite grâce au support de Spring Data JPA.
 *
 * ### Opérations héritées :
 * - `findAll()` → Récupère toutes les boissons.
 * - `findById(id: Long)` → Récupère une boisson par son identifiant.
 * - `save(entity: Boisson)` → Crée ou met à jour une boisson.
 * - `deleteById(id: Long)` → Supprime une boisson existante.
 *
 * @see com.restaurant.RestaurantApi.model.Boisson
 */
@Repository
interface BoissonRepository : JpaRepository<Boisson, Long> {


}