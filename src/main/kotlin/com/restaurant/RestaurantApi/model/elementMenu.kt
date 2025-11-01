package com.restaurant.RestaurantApi.model


import jakarta.persistence.*

@Entity
@Table(name = "elements_menu")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_element", discriminatorType = DiscriminatorType.STRING)
open class ElementMenu(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var nom: String,
    @Column(nullable = false)
    var prix: Double,
    @Column(nullable = false)
    var description: String,
)

