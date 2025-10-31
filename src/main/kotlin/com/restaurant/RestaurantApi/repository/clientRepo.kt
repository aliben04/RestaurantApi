package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {


}

