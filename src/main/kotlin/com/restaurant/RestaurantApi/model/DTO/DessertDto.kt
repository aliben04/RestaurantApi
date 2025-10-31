package com.restaurant.RestaurantApi.model.DTO

import jakarta.persistence.Column
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class DessertDto(
    val id: Long?,
    val nom: String,
    val prix: Double,
    val description: String,
    val calories: Int=0,
    val estServiChaud: Boolean=false

)