package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player is eliminated (dies with no bed).
 */
class ArenaPlayerEliminateEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val playerRef: PlayerRef,
    val team: cz.creeperface.hytale.bedwars.api.arena.Team
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
