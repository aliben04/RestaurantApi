package com.restaurant.RestaurantApi.model.DTO

import java.util.Date

data class CommandeDTO(
    val id: Long?,
    val date: Date,
    val clientId: Long,
    val elementsIds: List<Long>
)