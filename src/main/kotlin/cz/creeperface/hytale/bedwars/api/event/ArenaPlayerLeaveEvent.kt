package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player leaves an arena.
 */
class ArenaPlayerLeaveEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val playerRef: PlayerRef
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
