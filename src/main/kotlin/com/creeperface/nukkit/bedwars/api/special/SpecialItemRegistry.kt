package com.creeperface.nukkit.bedwars.api.special

/**
 * Registry for custom special items.
 * Third-party plugins register items here; the core handles triggering [SpecialItem.onUse].
 */
interface SpecialItemRegistry {

    /** Register a special item. */
    fun register(item: SpecialItem)

    /** Unregister by item ID. */
    fun unregister(itemId: String)

    /** Get a special item by its Hytale item ID. */
    fun get(itemId: String): SpecialItem?

    /** Get all registered special items. */
    fun getAll(): Collection<SpecialItem>
}
