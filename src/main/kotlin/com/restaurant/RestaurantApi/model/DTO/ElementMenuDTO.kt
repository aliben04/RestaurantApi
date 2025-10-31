package com.restaurant.RestaurantApi.model.DTO

data class ElementMenuDTO(
    val id: Long? = null,
    val nom: String,
    val prix: Double,
    val description: String
)