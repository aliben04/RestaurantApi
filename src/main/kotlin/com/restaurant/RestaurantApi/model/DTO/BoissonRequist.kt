package com.restaurant.RestaurantApi.model.DTO


import jakarta.validation.constraints.*

/**
 * Objet de transfert de données (DTO) pour la création ou la mise à jour d'une [com.restaurant.RestaurantApi.model.Boisson].
 *
 * Cette classe est utilisée pour valider les données reçues dans les requêtes HTTP
 * (POST et PUT) liées aux boissons dans le contrôleur correspondant.
 *
 * Elle contient toutes les propriétés nécessaires à la création d’un objet métier [Boisson],
 * avec des contraintes de validation assurant la cohérence et la qualité des données.
 *
 * ### Validation :
 * Chaque champ est annoté avec des contraintes [jakarta.validation] :
 * - [Pattern], [NotBlank], [NotNull], [Positive], [Size]
 *
 * ### Exemple JSON valide :
 * ```json
 * {
 *   "nom": "Jus d'Orange",
 *   "prix": 4.5,
 *   "description": "Boisson fraîche pressée à base d'oranges naturelles",
 *   "volumeLitre": 0.25,
 *   "contientAlcool": false
 * }
 * ```
 * @property nom Nom de la boisson.
 *  - Doit **commencer par une majuscule**.
 *  - Peut contenir **lettres, accents, espaces, tirets et apostrophes**.
 *  - Longueur comprise entre **3 et 50 caractères**.
 *  - Ne peut pas être vide ni nul.
 *
 * @property prix Prix de la boisson.
 *  - Doit être **strictement positif**.
 *
 * @property description Description détaillée de la boisson.
 *  - Longueur comprise entre **5 et 255 caractères**.
 *  - Ne peut pas être vide.
 *
 * @property volumeLitre Volume de la boisson en litres.
 *  - Doit être **positif** (ex : `0.33` pour 33 cl).
 *
 * @property contientAlcool Indique si la boisson contient de l’alcool.
 *  - `true` → boisson alcoolisée.
 *  - `false` → boisson non alcoolisée.
 */

data class BoissonRequest(
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
    val description: String,

    @field:Positive(message = "Le volume doit être positif")
    val volumeLitre: Double,

    val contientAlcool: Boolean
)
