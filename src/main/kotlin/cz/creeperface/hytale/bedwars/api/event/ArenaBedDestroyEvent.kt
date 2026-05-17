package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.ICancellable

/**
 * Fired when a bed is about to be destroyed. Cancellable.
 */
class ArenaBedDestroyEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val playerData: cz.creeperface.hytale.bedwars.api.arena.PlayerData,
    val team: cz.creeperface.hytale.bedwars.api.arena.Team
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena), ICancellable {

    private var cancelled: Boolean = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
