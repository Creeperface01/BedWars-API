package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.Team

/**
 * Fired when an arena game ends.
 */
class ArenaStopEvent(
    arena: Arena,
    val winner: Team?,
    val cause: Cause
) : ArenaEvent(arena) {

    enum class Cause {
        ELIMINATION,
        NO_PLAYERS,
        TIME_LIMIT,
        SHUTDOWN,
        COMMAND,
        CUSTOM
    }
}
