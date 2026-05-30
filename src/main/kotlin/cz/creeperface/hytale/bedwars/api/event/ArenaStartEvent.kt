package cz.creeperface.hytale.bedwars.api.event

import cz.creeperface.hytale.bedwars.api.arena.Arena

/**
 * Fired when an arena transitions to GAME state (game begins).
 */
class ArenaStartEvent(arena: Arena) :
    ArenaEvent(arena)
