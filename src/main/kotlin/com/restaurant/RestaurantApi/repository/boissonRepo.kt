package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Boisson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoissonRepository : JpaRepository<Boisson, Long> {


}