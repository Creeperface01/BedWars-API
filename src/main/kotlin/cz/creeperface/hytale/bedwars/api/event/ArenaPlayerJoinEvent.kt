package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.ICancellable
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player is about to join an arena. Cancellable.
 */
class ArenaPlayerJoinEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val playerRef: PlayerRef
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena), ICancellable {

    private var cancelled: Boolean = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
