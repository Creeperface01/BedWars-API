package cz.creeperface.hytale.bedwars.api.event

/**
 * Fired when an arena transitions between states (waiting → voting → team_select → game).
 */
class ArenaStateChangeEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val previousState: cz.creeperface.hytale.bedwars.api.arena.State<*>,
    val newState: cz.creeperface.hytale.bedwars.api.arena.State<*>
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
