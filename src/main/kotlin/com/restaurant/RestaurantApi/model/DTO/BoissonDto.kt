package com.restaurant.RestaurantApi.model.DTO


data class BoissonDTO(
    val id: Long?,
    val nom: String,
    val prix: Double,
    val description: String,
    val volumeLitre: Double,
    val contientAlcool: Boolean
)
