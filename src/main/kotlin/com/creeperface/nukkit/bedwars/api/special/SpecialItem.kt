package com.creeperface.nukkit.bedwars.api.special

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.PlayerData
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * A special item with custom behavior when used during a BedWars game.
 * Register via [SpecialItemRegistry.register].
 *
 * Examples: bridge eggs, fireballs, teleport pearls, TNT mines.
 */
interface SpecialItem {

    /** Unique identifier (matches the Hytale item asset ID). */
    val itemId: String

    /** Display name for the shop/UI. */
    val displayName: String

    /**
     * Called when a player uses this item during a game.
     * @return true if the item should be consumed (removed from inventory)
     */
    fun onUse(playerRef: PlayerRef, playerData: PlayerData, arena: Arena): Boolean
}
