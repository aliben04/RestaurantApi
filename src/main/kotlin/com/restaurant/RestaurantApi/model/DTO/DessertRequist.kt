package com.restaurant.RestaurantApi.model.DTO

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive

data class DessertRequist(
    @field:Pattern(
        regexp = "^[A-Z][a-zA-ZÀ-ÿ\\s'-]{2,50}$",
        message = "Le nom doit commencer par une majuscule et contenir uniquement des lettres et des espaces"
    )
    @field:NotNull(message = "Le nom not null")
    @field:NotBlank(message = "Le nom ne doit pas être vide")
    val nom: String,
    @field:Positive(message = "Le prix doit être positif")
    val prix: Double,
    @field:NotBlank(message = "La description est obligatoire")
    val description: String,

    @field:Min(value = 0, message = "Le nombre de calories ne peut pas être négatif.")
    @field:Max(value = 10000, message = "Le nombre maximum de calories autorisées est 10000.")

    var calories: Int=0,

    var estServiChaud: Boolean=false
)