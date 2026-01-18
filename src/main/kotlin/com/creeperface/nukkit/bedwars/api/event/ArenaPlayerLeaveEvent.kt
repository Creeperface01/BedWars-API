package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player leaves an arena.
 */
class ArenaPlayerLeaveEvent(
    arena: Arena,
    val playerRef: PlayerRef
) : ArenaEvent(arena)
