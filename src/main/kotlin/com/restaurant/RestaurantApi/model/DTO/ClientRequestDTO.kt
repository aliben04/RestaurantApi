package com.restaurant.RestaurantApi.model.DTO

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class ClientRequestDTO(
    @field:Pattern(
        regexp = "^[A-Z][a-zA-ZÀ-ÿ\\s'-]{2,50}$",
        message = "Le nom doit commencer par une majuscule et contenir uniquement des lettres et des espaces"
    )
    @field:NotNull(message = "Le nom not null")
    val nom: String
)