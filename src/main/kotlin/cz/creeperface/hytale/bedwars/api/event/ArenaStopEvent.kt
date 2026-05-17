package cz.creeperface.hytale.bedwars.api.event

/**
 * Fired when an arena game ends.
 */
class ArenaStopEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val winner: cz.creeperface.hytale.bedwars.api.arena.Team?,
    val cause: Cause
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena) {

    enum class Cause {
        ELIMINATION,
        NO_PLAYERS,
        TIME_LIMIT,
        SHUTDOWN,
        COMMAND,
        CUSTOM
    }
}
