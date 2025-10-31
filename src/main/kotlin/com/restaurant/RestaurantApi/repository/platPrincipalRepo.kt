package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.PlatPrincipal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface PlatPrincipalRepostory: JpaRepository<PlatPrincipal,Long>{

}