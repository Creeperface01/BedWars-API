package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.Team
import com.creeperface.nukkit.bedwars.api.arena.configuration.Vec3
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player respawns at their team spawn.
 */
class ArenaPlayerRespawnEvent(
    arena: Arena,
    val playerRef: PlayerRef,
    val team: Team,
    val spawnPosition: Vec3
) : ArenaEvent(arena)
