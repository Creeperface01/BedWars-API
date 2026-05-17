package cz.creeperface.hytale.bedwars.api.event

/**
 * Fired when an entire team is eliminated (bed destroyed and all players dead).
 */
class ArenaTeamEliminateEvent(
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val team: cz.creeperface.hytale.bedwars.api.arena.Team
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
