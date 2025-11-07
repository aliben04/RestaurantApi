package com.restaurant.RestaurantApi.model

import jakarta.persistence.*

/**
 * Entité représentant un **plat** dans le système de gestion du restaurant.
 *
 * Cette classe hérite de [ElementMenu] et ajoute des attributs spécifiques
 * aux plat tels que le nombre de  et le mode de service (tempsPreparationMinutes).
 *
 * Elle est stockée dans la même table que les autres types d’éléments du menu
 * grâce à l’héritage **JPA avec discrimination** (`@DiscriminatorValue("PLAT")`).
 *
 * ### Exemple d'objet :
 * ```kotlin
 * Dessert(
 *   nom = "Fondant au Chocolat",
 *   prix = 5.99,
 *   description = "Dessert chaud au chocolat fondant et glace vanille",
 *   tempsPreparationMinutes = 20
 * )
 * ```
 *
 * @property tempsPreparationMinutes Nombre de  min dans le plaprincipal.
 *  - Valeur entière comprise entre **0 et  180** selon la validation du DTO.
 */
@Entity
@DiscriminatorValue("PLAT")

 class PlatPrincipal(

     nom: String,
     prix: Double,
     description: String,

    @Column(nullable = false)
     var tempsPreparationMinutes: Int = 0

) : ElementMenu(null,nom, prix, description)