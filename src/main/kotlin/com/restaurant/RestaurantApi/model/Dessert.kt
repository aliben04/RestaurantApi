package com.restaurant.RestaurantApi.model


import jakarta.persistence.*

/**
 * Entité représentant un **dessert** dans le système de gestion du restaurant.
 *
 * Cette classe hérite de [ElementMenu] et ajoute des attributs spécifiques
 * aux desserts tels que le nombre de calories et le mode de service (chaud ou froid).
 *
 * Elle est stockée dans la même table que les autres types d’éléments du menu
 * grâce à l’héritage **JPA avec discrimination** (`@DiscriminatorValue("DESSERT")`).
 *
 * @property calories Nombre de calories contenues dans le dessert.
 *  - Valeur entière comprise entre **0 et 10 000** selon la validation du DTO.
 * @property estServiChaud Indique si le dessert est servi chaud (`true`) ou froid (`false`).
 */
@Entity
@DiscriminatorValue("DESSERT")
class Dessert(
    nom: String,
    prix: Double,
    description: String,
    var calories: Int=0,
    @Column(nullable = false)
    var estServiChaud: Boolean=false

) : ElementMenu(null,nom, prix, description)