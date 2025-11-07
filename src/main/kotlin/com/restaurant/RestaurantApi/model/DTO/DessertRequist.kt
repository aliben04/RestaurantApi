package com.restaurant.RestaurantApi.model.DTO



import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive

/**
 * Représente la requête utilisée pour créer ou mettre à jour un **dessert** dans l’API REST.
 *
 * Cette classe est un **Data Transfer Object (DTO)** qui encapsule les données
 * envoyées par le client lors des opérations `POST` ou `PUT`.
 * Elle intègre plusieurs **contraintes de validation** pour garantir
 * la cohérence et la validité des informations saisies.
 *
 * ### Contraintes de validation :
 * - `nom` : Doit commencer par une majuscule et ne contenir que des lettres ou espaces.
 * - `prix` : Doit être un nombre strictement positif.
 * - `description` : Doit être renseignée et non vide.
 * - `calories` : Doit être compris entre 0 et 10 000.
 * - `estServiChaud` : Indique si le dessert est servi chaud (`true`) ou froid (`false`).
 *
 * ### Exemple d'utilisation :
 * ```json
 * {
 *   "nom": "Tarte au Chocolat",
 *   "prix": 6.5,
 *   "description": "Délicieuse tarte au chocolat noir et chantilly",
 *   "calories": 450,
 *   "estServiChaud": false
 * }
 * ```
 *
 * @property nom Nom du dessert (obligatoire, commence par une majuscule).
 * @property prix Prix du dessert (obligatoire, positif).
 * @property description Description détaillée du dessert.
 * @property calories Nombre de calories (valeur entre 0 et 10 000).
 * @property estServiChaud Indique si le dessert est servi chaud (`true`) ou froid (`false`).
 */
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