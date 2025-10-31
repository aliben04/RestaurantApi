package com.restaurant.RestaurantApi.model.DTO

import java.util.Date

data class CommandeResponse(
    val id: Long?,
    val date: Date,
    val clientName: String,
    val elements: List<ElementMenuDTO>
)