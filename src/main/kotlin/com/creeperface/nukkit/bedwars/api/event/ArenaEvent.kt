package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.hypixel.hytale.event.IEvent

/**
 * Base class for all BedWars arena events.
 * Implements [IEvent] for Hytale event bus compatibility.
 */
open class ArenaEvent(
    val arena: Arena
) : IEvent<Void>
