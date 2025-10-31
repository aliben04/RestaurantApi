package com.restaurant.RestaurantApi.model

import jakarta.persistence.*

@Entity
@DiscriminatorValue("BOISSON")
class Boisson(

    nom: String,
    prix: Double,
    description: String,

    @Column(nullable = false)
    var volumeLitre: Double=0.0,

    @Column(nullable = false)
    var contientAlcool: Boolean = false

) : ElementMenu(null,nom, prix, description)