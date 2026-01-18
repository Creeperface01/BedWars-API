package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.State

/**
 * Fired when an arena transitions between states (waiting → voting → team_select → game).
 */
class ArenaStateChangeEvent(
    arena: Arena,
    val previousState: State<*>,
    val newState: State<*>
) : ArenaEvent(arena)
