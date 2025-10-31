package com.restaurant.RestaurantApi.model

import jakarta.persistence.*
import jakarta.validation.constraints.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

@Entity
@DiscriminatorValue("PLAT")

 class PlatPrincipal(

     nom: String,
     prix: Double,
     description: String,

    @Column(nullable = false)
     var tempsPreparationMinutes: Int = 0

) : ElementMenu(null,nom, prix, description)