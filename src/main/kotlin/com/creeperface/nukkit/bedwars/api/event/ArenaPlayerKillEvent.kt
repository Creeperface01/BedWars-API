package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Fired when a player is killed by another player during a game.
 */
class ArenaPlayerKillEvent(
    arena: Arena,
    val victim: PlayerRef,
    val killer: PlayerRef
) : ArenaEvent(arena)
