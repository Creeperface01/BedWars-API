package cz.creeperface.hytale.bedwars.api.event

/**
 * Fired when an arena transitions to GAME state (game begins).
 */
class ArenaStartEvent(arena: cz.creeperface.hytale.bedwars.api.arena.Arena) :
    cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
