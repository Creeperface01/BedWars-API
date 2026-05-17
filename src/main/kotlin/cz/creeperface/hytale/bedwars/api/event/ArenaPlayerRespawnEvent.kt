package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player respawns at their team spawn.
 */
class ArenaPlayerRespawnEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val playerRef: PlayerRef,
    val team: cz.creeperface.hytale.bedwars.api.arena.Team,
    val spawnPosition: cz.creeperface.hytale.bedwars.api.arena.configuration.Vec3
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
