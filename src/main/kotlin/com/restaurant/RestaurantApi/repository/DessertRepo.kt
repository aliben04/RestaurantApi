package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Dessert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DessertRepository : JpaRepository<Dessert, Long> {


}