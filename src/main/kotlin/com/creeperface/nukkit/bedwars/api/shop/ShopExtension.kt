package com.creeperface.nukkit.bedwars.api.shop

/**
 * API for extending the shop dynamically from other plugins.
 * Access via [com.creeperface.nukkit.bedwars.api.BedWarsAPI].
 */
interface ShopExtension {

    /** Add a category to the shop. Returns the category index. */
    fun addCategory(name: String, iconItemId: String): Int

    /** Remove a category by index. */
    fun removeCategory(index: Int)

    /** Add an offer item to a category. Returns the item index within the category. */
    fun addItem(
        categoryIndex: Int,
        name: String,
        itemId: String,
        itemCount: Int,
        costs: List<ShopCost>
    ): Int

    /** Remove an item from a category. */
    fun removeItem(categoryIndex: Int, itemIndex: Int)

    /** Modify an existing item's cost. */
    fun setItemCost(categoryIndex: Int, itemIndex: Int, costs: List<ShopCost>)

    /** Get the number of categories. */
    fun getCategoryCount(): Int

    /** Get the number of items in a category. */
    fun getItemCount(categoryIndex: Int): Int
}

data class ShopCost(
    val itemId: String,
    val itemCount: Int
)
