package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.Team

/**
 * Fired when an entire team is eliminated (bed destroyed and all players dead).
 */
class ArenaTeamEliminateEvent(
    arena: Arena,
    val team: Team
) : ArenaEvent(arena)
