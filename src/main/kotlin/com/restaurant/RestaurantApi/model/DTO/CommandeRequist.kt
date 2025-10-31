package com.restaurant.RestaurantApi.model.DTO
import java.util.*

data class CommandeRequest(
    val date: Date,
    val clientId: Long,
    val elementIds: List<Long>
)