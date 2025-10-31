package com.restaurant.RestaurantApi.model.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

class ElementMenuRequist(
    @field:Pattern(
        regexp = "^[A-Z][a-zA-ZÀ-ÿ\\s'-]{2,50}$",
        message = "Le nom doit commencer par une majuscule et contenir uniquement des lettres et des espaces"
    )
    @field:NotBlank(message = "Le nom ne doit pas être vide")
    @field:NotNull(message = "Le nom not null")
    val nom: String,

    @field:Positive(message = "Le prix doit être positif")
    val prix: Double,
    @field:Size(min = 5, max = 255, message = "La description doit contenir entre 5 et 255 caractères")
    @field:NotBlank(message = "La description est obligatoire")
    val description: String
)