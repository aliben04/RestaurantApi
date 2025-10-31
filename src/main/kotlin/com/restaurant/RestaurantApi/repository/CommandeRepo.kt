package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Commande
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Date
@Repository

interface CommandeRepository : JpaRepository<Commande, Long> {


}