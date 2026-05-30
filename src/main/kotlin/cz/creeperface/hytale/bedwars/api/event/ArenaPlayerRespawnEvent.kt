package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.Team
import cz.creeperface.hytale.bedwars.api.arena.configuration.Vec3

/**
 * Fired when a player respawns at their team spawn.
 */
class ArenaPlayerRespawnEvent(
    arena: Arena,
    val playerRef: PlayerRef,
    val team: Team,
    val spawnPosition: Vec3
) : ArenaEvent(arena)
