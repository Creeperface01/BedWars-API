package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.hypixel.hytale.event.ICancellable
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player is about to join an arena. Cancellable.
 */
class ArenaPlayerJoinEvent(
    arena: Arena,
    val playerRef: PlayerRef
) : ArenaEvent(arena), ICancellable {

    private var cancelled: Boolean = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
