package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.ICancellable
import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.PlayerData
import cz.creeperface.hytale.bedwars.api.arena.Team

/**
 * Fired when a bed is about to be destroyed. Cancellable.
 */
class ArenaBedDestroyEvent(
    arena: Arena,
    val playerData: PlayerData,
    val team: Team
) : ArenaEvent(arena), ICancellable {

    private var cancelled: Boolean = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
