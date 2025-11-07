package com.restaurant.RestaurantApi.controller


import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
/**
 * Gestionnaire global d'exceptions pour l'application REST.
 *
 * Cette classe utilise [RestControllerAdvice] pour intercepter les exceptions
 * levées par n'importe quel [RestController] de l'application.
 *
 * Elle centralise la gestion des erreurs et la transforme en réponses
 * HTTP claires et standardisées (ex: 400, 404, 500) au format JSON.
 *
 * @see RestControllerAdvice
 * @see ExceptionHandler
 */

@RestControllerAdvice
class GlobalExceptionHandler {
    /**
     * Gère les échecs de validation des DTO (Data Transfer Objects).
     *
     * Cette méthode est déclenchée automatiquement lorsque les annotations de
     * validation (`@Valid`) sur un [org.springframework.web.bind.annotation.RequestBody]
     * échouent (par exemple, `@NotBlank`, `@Size`, `@Positive`).
     *
     * Elle intercepte [MethodArgumentNotValidException] et retourne un
     * statut HTTP 400 (Bad Request).
     *
     * @param ex L'exception [MethodArgumentNotValidException] contenant
     * les détails des erreurs de validation.
     * @return Une `Map` où la clé est le nom du champ qui a échoué
     * la validation (ex: "nom") et la valeur est le message d'erreur
     * (ex: "Le nom ne doit pas être vide").
     *
     * **Exemple de requête (invalide) :**
     * (En supposant un DTO [com.restaurant.RestaurantApi.model.DTO.PlatPrincipalRequest])
     * ```
     * POST http://localhost:8080/api/plats
     * Content-Type: application/json
     *
     * {
     * "nom": "a",
     * "prix": -10.0,
     * "description": "test",
     * "tempsPreparationMinutes": 2
     * }
     * ```
     *
     * **Réponse (HTTP 400 Bad Request) :**
     * ```json
     * {
     * "nom": "Le nom doit commencer par une majuscule et contenir entre 2 et 100 caractères",
     * "prix": "Le prix doit être un nombre positif.",
     * "description": "La description doit contenir entre 5 et 255 caractères",
     * "tempsPreparationMinutes": "Le temps de préparation minimum doit être de 5 minutes."
     * }
     * ```
     */

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String> {

        val errors = mutableMapOf<String, String>()


        ex.bindingResult.fieldErrors.forEach { error ->
            val fieldName = error.field
            val errorMessage = error.defaultMessage ?: "Erreur inconnue lors de la vérification"


            errors[fieldName] = errorMessage
        }

        return errors
    }
}