package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.PlatPrincipal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
/**
 * Repository JPA pour les entités [PlatPrincipal].
 *
 * Fournit les opérations CRUD de base :
 * - `findAll()` : récupère tous les plats principaux
 * - `findById(id)` : recherche un plat par son ID
 * - `save(entity)` : ajoute ou met à jour un plat
 * - `deleteById(id)` : supprime un plat
 */
@Repository
interface PlatPrincipalRepostory: JpaRepository<PlatPrincipal,Long>{

}