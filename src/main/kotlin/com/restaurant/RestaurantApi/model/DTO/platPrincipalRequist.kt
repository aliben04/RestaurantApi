package com.restaurant.RestaurantApi.model.DTO

import jakarta.validation.constraints.*

class platPrincipalRequist (
    @field:Pattern(
        regexp = "^[A-Z][a-zA-ZÀ-ÿ\\s'-]{2,50}$",
        message = "Le nom doit commencer par une majuscule et contenir uniquement des lettres et des espaces"
    )
    @field:Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    @field:NotBlank(message = "Le nom ne doit pas être vide")
    val nom: String,
    val description: String,

    @field:Min(value = 5, message = "Le temps de préparation minimum doit être de 5 minutes.")
    @field:Max(value = 120, message = "Le temps de préparation ne peut pas dépasser 120 minutes (2 heures).")
    val prix: Double,
    var tempsPreparationMinutes: Int = 0
)