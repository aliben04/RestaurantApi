package com.restaurant.RestaurantApi.model
import jakarta.persistence.*


@Entity
@Table(name="client")
 class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    var nom: String
) {
     @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
    val commandes: MutableList<Commande> = mutableListOf()
}

