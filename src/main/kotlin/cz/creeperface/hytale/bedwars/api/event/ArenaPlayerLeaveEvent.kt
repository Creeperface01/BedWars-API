package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.Arena

/**
 * Fired when a player leaves an arena.
 */
class ArenaPlayerLeaveEvent(
    arena: Arena,
    val playerRef: PlayerRef
) : ArenaEvent(arena)
