package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.Team

/**
 * Fired when a player is eliminated (dies with no bed).
 */
class ArenaPlayerEliminateEvent(
    arena: Arena,
    val playerRef: PlayerRef,
    val team: Team
) : ArenaEvent(arena)
