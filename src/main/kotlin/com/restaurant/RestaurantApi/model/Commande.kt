package com.restaurant.RestaurantApi.model
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "Commande")
class Commande(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var date: Date,

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    var client: Client,

    @ManyToMany
    @JoinTable(
        name = "commande_menu",
        joinColumns = [JoinColumn(name = "commande_id")],
        inverseJoinColumns = [JoinColumn(name = "menu_id")]
    )
    val elements: MutableList<ElementMenu> = mutableListOf()
)
