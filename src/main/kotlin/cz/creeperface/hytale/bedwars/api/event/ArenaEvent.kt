package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.IEvent

/**
 * Base class for all BedWars arena events.
 * Implements [IEvent] for Hytale event bus compatibility.
 */
open class ArenaEvent(
    val arena: cz.creeperface.hytale.bedwars.api.arena.Arena
) : IEvent<Void>
