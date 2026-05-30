package cz.creeperface.hytale.bedwars.api.event

import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.Team

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
