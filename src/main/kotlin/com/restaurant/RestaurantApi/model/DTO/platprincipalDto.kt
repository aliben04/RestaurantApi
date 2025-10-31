package com.restaurant.RestaurantApi.model.DTO


class platprincipalDto(
    val id: Long? = null,
    val nom: String,
    val prix: Double,
    val description: String,
    var tempsPreparationMinutes: Int
)