package com.restaurant.RestaurantApi.model


import jakarta.persistence.*

/**
 * Représente un élément du menu dans le restaurant.
 *
 * Cette entité correspond à une ligne dans la table `element_menu` de la base de données.
 * Un élément du menu peut être un plat, une boisson, ou un dessert.
 *
 * @property id Identifiant unique de l’élément.
 * @property nom Nom du plat ou de l’élément du menu (ex : "Pizza Margherita").
 * @property prix Prix de l’élément en devise locale.
 * @property description Description textuelle du plat ou de l’élément.
 * ### Exemple JSON :
 * ```json
 *  {
 *     "nom": "Coca-Cola",
 *     "prix": 3.5,
 *     "description": "Boisson gazeuse rafraîchissante",
 *     "volumeLitre": 0.33,
 *     "contientAlcool": false
 *  }
 *  ```
 */
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

