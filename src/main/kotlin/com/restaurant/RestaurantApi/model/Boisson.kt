package com.restaurant.RestaurantApi.model

import jakarta.persistence.*
/**
 * Entité représentant une **boisson** dans le menu du restaurant.
 *
 * Cette classe hérite de [ElementMenu] et correspond à un enregistrement spécifique
 * dans une hiérarchie d’héritage utilisant la stratégie **Single Table Inheritance (STI)**.
 *
 * Le type de cet élément est défini par la valeur de discrimination `BOISSON`
 * grâce à l’annotation [DiscriminatorValue].
 *
 * ### Héritage :
 * - Classe parente : [ElementMenu]
 * - Type de discrimination : `"BOISSON"`
 *
 * ### Champs spécifiques :
 * - [volumeLitre] : quantité de la boisson servie en litres.
 * - [contientAlcool] : indique si la boisson contient de l’alcool.
 *
 * ### Exemple JSON :
 * ```json
 * {
 *   "nom": "Coca-Cola",
 *   "prix": 3.5,
 *   "description": "Boisson gazeuse rafraîchissante",
 *   "volumeLitre": 0.33,
 *   "contientAlcool": false
 * }
 * ```
 *
 * @property volumeLitre Volume de la boisson exprimé en litres.
 *  - Ne peut pas être nul.
 *  - Exemple : `0.5` pour une bouteille de 50 cl.
 *
 * @property contientAlcool Indique si la boisson contient de l’alcool.
 *  - `true` → boisson alcoolisée (ex : vin, bière).
 *  - `false` → boisson non alcoolisée (ex : jus, soda).
 *
 * @constructor Crée une nouvelle instance de [Boisson].
 * @param nom Nom de la boisson (hérité de [ElementMenu]).
 * @param prix Prix de la boisson.
 * @param description Brève description de la boisson.
 * @param volumeLitre Volume de la boisson en litres (par défaut `0.0`).
 * @param contientAlcool Indique si la boisson contient de l’alcool (par défaut `false`).
 */
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