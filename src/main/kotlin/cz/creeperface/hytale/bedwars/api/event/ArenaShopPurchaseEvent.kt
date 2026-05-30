package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.ICancellable
import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.Arena

/**
 * Fired when a player is about to purchase an item from the shop. Cancellable.
 * Cancel to prevent the purchase.
 */
class ArenaShopPurchaseEvent(
    arena: Arena,
    val playerRef: PlayerRef,
    /** Item being purchased (Hytale item ID). */
    val itemId: String,
    val itemCount: Int,
    /** Cost item IDs and amounts. */
    val costs: List<Pair<String, Int>>
) : ArenaEvent(arena), ICancellable {

    private var cancelled = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
