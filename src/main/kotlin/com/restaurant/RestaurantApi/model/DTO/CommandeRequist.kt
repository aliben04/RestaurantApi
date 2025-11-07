package com.restaurant.RestaurantApi.model.DTO
import java.util.*
/**
 * DTO (Data Transfer Object) pour la création d'une nouvelle [Commande].
 *
 * Cette `data class` est utilisée pour recevoir les informations d'une requête API
 * (généralement un JSON) nécessaires à la création d'une commande.
 * Elle ne contient que les identifiants des entités liées (Client et ElementMenu).
 *
 * @property date La date et l'heure auxquelles la commande est passée.
 * @property clientId L'identifiant unique (ID) du [Client] qui passe la commande.
 * @property elementIds Une liste des identifiants (IDs) des [ElementMenu] inclus dans cette commande.
 *
 * @see com.restaurant.RestaurantApi.model.Commande
 * @see com.restaurant.RestaurantApi.model.Client
 * @see com.restaurant.RestaurantApi.model.ElementMenu
 */
data class CommandeRequest(
    val date: Date,
    val clientId: Long,
    val elementIds: List<Long>
)