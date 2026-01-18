package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.Team
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player is eliminated (dies with no bed).
 */
class ArenaPlayerEliminateEvent(
    arena: Arena,
    val playerRef: PlayerRef,
    val team: Team
) : ArenaEvent(arena)
