package cz.creeperface.hytale.bedwars.api.event

import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.Team

/**
 * Fired when an entire team is eliminated (bed destroyed and all players dead).
 */
class ArenaTeamEliminateEvent(
    arena: Arena,
    val team: Team
) : ArenaEvent(arena)
