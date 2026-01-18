package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena

/**
 * Fired when an arena transitions to GAME state (game begins).
 */
class ArenaStartEvent(arena: Arena) : ArenaEvent(arena)
