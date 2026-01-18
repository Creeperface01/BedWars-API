package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.configuration.Vec3
import com.hypixel.hytale.event.ICancellable

/**
 * Fired when a resource is about to be dropped at a generator. Cancellable.
 * Cancel to prevent this specific resource drop.
 */
class ArenaResourceDropEvent(
    arena: Arena,
    val itemId: String,
    val position: Vec3,
    /** The generator name from configuration. */
    val generatorName: String
) : ArenaEvent(arena), ICancellable {

    private var cancelled = false

    override fun isCancelled() = cancelled
    override fun setCancelled(value: Boolean) {
        cancelled = value
    }
}
