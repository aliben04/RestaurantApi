package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Dessert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
/**
 * Interface de persistance pour l'entité [Dessert].
 *
 * Hérite de [JpaRepository] pour fournir des méthodes CRUD standards :
 * - `findAll()` : récupère tous les desserts
 * - `findById(id)` : recherche un dessert par identifiant
 * - `save(entity)` : ajoute ou met à jour un dessert
 * - `deleteById(id)` : supprime un dessert
 *
 * Cette interface peut être étendue ultérieurement pour définir
 * des requêtes personnalisées via **Spring Data JPA**.
 */

@Repository
interface DessertRepository : JpaRepository<Dessert, Long> {


}