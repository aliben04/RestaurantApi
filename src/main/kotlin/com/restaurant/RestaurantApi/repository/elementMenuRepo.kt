package com.restaurant.RestaurantApi.repository

import com.restaurant.RestaurantApi.model.ElementMenu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface ElementMenuRepository : JpaRepository<ElementMenu, Long> {


}