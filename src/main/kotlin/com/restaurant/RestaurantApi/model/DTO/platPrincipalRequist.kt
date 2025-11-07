package com.restaurant.RestaurantApi.model.DTO

import jakarta.validation.constraints.*
/**
 * Représente les données nécessaires pour créer ou mettre à jour un **Plat Principal**.
 *
 * Cette classe est un **DTO** utilisé pour transférer les informations
 * depuis les requêtes HTTP (`POST` et `PUT`) vers le service métier.
 *
 * ### Contraintes de validation :
 * - `nom` : Commence par une majuscule, lettres et espaces autorisés, 2 à 100 caractères.
 * - `description` : Description textuelle du plat.
 * - `prix` : Doit être supérieur ou égal à 5 et inférieur ou égal à 120.
 * - `tempsPreparationMinutes` : Durée de préparation en minutes.
 *
 * ### Exemple JSON :
 * ```json
 * {
 *   "nom": "Poulet Rôti",
 *   "description": "Poulet rôti au four avec herbes de Provence",
 *   "prix": 25.0,
 *   "tempsPreparationMinutes": 45
 * }
 * ```
 *
 * @property nom Nom du plat principal.
 * @property description Description détaillée du plat.
 * @property prix Prix du plat en unités monétaires.
 * @property tempsPreparationMinutes Temps de préparation en minutes.
 */
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