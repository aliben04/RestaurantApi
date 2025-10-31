package com.restaurant.RestaurantApi.model


import jakarta.persistence.*

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