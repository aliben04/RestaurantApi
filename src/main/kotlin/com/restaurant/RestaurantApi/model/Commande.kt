package com.restaurant.RestaurantApi.model
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*
/**
 * Représente une commande unique passée dans le restaurant.
 *
 * Cette classe est une entité JPA mappée à la table `Commande`. Elle sert de
 * lien entre un [Client] et les [ElementMenu] qu'il a commandés.
 *
 * @property id L'identifiant unique de la commande.
 * Il est généré automatiquement par la base de données.
 * @property date La date et l'heure exactes auxquelles la commande a été passée.
 * Ce champ ne peut pas être nul.
 * @property client Le [Client] qui a passé cette commande.
 * Il s'agit d'une relation `ManyToOne` (plusieurs commandes appartiennent à un client).
 * L'annotation [@JsonIgnore] est utilisée pour empêcher les boucles de sérialisation
 * infinies (Client -> Commandes -> Client) lors de la conversion en JSON.
 */
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
    /**
     * La liste des [ElementMenu] (articles) inclus dans cette commande.
     *
     * Il s'agit d'une relation `ManyToMany` (une commande peut avoir plusieurs
     * articles, et un article peut être dans plusieurs commandes).
     * La relation est gérée par une table de jonction nommée `commande_menu`.
     */
    @ManyToMany
    @JoinTable(
        name = "commande_menu",
        joinColumns = [JoinColumn(name = "commande_id")],
        inverseJoinColumns = [JoinColumn(name = "menu_id")]
    )
    val elements: MutableList<ElementMenu> = mutableListOf()
)
