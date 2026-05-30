package cz.creeperface.hytale.bedwars.api.event

import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.State

/**
 * Fired when an arena transitions between states (waiting → voting → team_select → game).
 */
class ArenaStateChangeEvent(
    arena: Arena,
    val previousState: State<*>,
    val newState: State<*>
) : ArenaEvent(arena)
