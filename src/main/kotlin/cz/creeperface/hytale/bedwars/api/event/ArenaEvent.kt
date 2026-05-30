package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.event.IEvent
import cz.creeperface.hytale.bedwars.api.arena.Arena

/**
 * Base class for all BedWars arena events.
 * Implements [IEvent] for Hytale event bus compatibility.
 */
open class ArenaEvent(
    val arena: Arena
) : IEvent<Void>
