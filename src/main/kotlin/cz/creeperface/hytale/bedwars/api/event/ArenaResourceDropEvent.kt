package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.ICancellable

/**
 * Fired when a resource is about to be dropped at a generator. Cancellable.
 * Cancel to prevent this specific resource drop.
 */
class ArenaResourceDropEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val itemId: String,
    val position: cz.creeperface.hytale.bedwars.api.arena.configuration.Vec3,
    /** The generator name from configuration. */
    val generatorName: String
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena), ICancellable {

    private var cancelled = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
