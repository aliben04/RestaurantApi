package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.ElementMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
/**
 * Interface Repository pour l'entité [ElementMenu].
 *
 * Elle hérite de [JpaRepository] pour fournir les opérations CRUD de base (POST, GET, PUT, DELETE)
 * pour la classe mère [ElementMenu]. Grâce à la stratégie d'héritage SINGLE_TABLE définie

 *
 * @see ElementMenu
 */
@Repository

interface ElementMenuRepository : JpaRepository<ElementMenu, Long> {


}