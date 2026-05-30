package cz.creeperface.hytale.bedwars.api.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * Shop structure loaded from shop.json.
 */
@Serializable
data class ShopConfig(
    val windows: List<ShopWindowConfig> = emptyList(),
    val extensions: JsonObject = JsonObject(emptyMap())
)

@Serializable
data class ShopWindowConfig(
    val name: String = "",
    val icon: ShopItemRef = ShopItemRef(),
    val type: String = "menu",
    val children: List<ShopWindowConfig> = emptyList(),
    @SerialName("purchase_item") val purchaseItem: ShopItemRef? = null,
    val cost: List<ShopItemRef> = emptyList()
)

@Serializable
data class ShopItemRef(
    @SerialName("item_id") val itemId: String = "",
    @SerialName("item_count") val itemCount: Int = 1,
    @SerialName("item_name") val itemName: String? = null
)
