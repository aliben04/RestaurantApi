package com.restaurant.RestaurantApi.model.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
/**
 * DTO (Data Transfer Object) pour la création ou la mise à jour d'un [ElementMenu].
 *
 * Cette classe est utilisée pour transporter les données depuis la requête
 * (ex: JSON d'une API POST/PUT) vers le contrôleur.
 *
 * Elle inclut des annotations de validation (`jakarta.validation`) pour s'assurer
 * que les données sont conformes aux règles métier avant d'être traitées
 * par le [com.restaurant.RestaurantApi.service.ElementMenuService].
 *
 * @property nom Le nom de l'élément de menu.
 * - Ne doit pas être vide (`@NotBlank`) ou nul (`@NotNull`).
 * - Doit correspondre au pattern (`@Pattern`): commencer par une majuscule,
 * suivi de 2 à 50 caractères (lettres, espaces, apostrophes).
 * @property prix Le prix de l'élément de menu.
 * - Doit être un nombre strictement positif (`@Positive`).
 * @property description La description de l'élément de menu.
 * - Est obligatoire et ne doit pas être vide (`@NotBlank`).
 * - Doit contenir entre 5 et 255 caractères (`@Size`).
 */
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