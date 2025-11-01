package com.restaurant.RestaurantApi.model

import jakarta.persistence.*


@Entity
@DiscriminatorValue("PLAT")

 class PlatPrincipal(

     nom: String,
     prix: Double,
     description: String,

    @Column(nullable = false)
     var tempsPreparationMinutes: Int = 0

) : ElementMenu(null,nom, prix, description)