package com.restaurant.RestaurantApi.model
import jakarta.persistence.*

/**
 * Représente un client du restaurant.
 *
 * Cette classe est une entité JPA mappée à la table `client`. Elle stocke
 * les informations de base du client ainsi que ses commandes associées.
 *
 * @property id L'identifiant unique du client.
 * Il est généré automatiquement par la base de données lors de la création.
 * Peut être `null` avant la persistance.
 * @property nom Le nom complet du client. Ce champ ne peut pas être nul.
 */
@Entity
@Table(name="client")
 class Client(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    var nom: String
) {
    /**
     * La liste des commandes passées par ce client.
     *
     * Il s'agit d'une relation "un-à-plusieurs" (OneToMany) avec l'entité [Commande].
     * `mappedBy = "client"` indique que l'entité [Commande] gère la clé étrangère.
     * `CascadeType.ALL` signifie que les opérations (comme la sauvegarde ou
     * la suppression) sur un [Client] seront propagées à ses [commandes].
     */
     @OneToMany(mappedBy = "client", cascade = [CascadeType.ALL])
    val commandes: MutableList<Commande> = mutableListOf()
}

