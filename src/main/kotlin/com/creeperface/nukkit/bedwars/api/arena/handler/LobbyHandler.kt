package com.creeperface.nukkit.bedwars.api.arena.handler

import com.creeperface.nukkit.bedwars.api.arena.Team
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration
import com.hypixel.hytale.server.core.universe.PlayerRef

interface LobbyHandler {

    interface VotingHandler : LobbyHandler

    interface TeamSelectHandler : LobbyHandler {

        val teams: List<Team>

        val starting: Boolean

        val mapConfig: MapConfiguration

        fun getPlayerTeam(p: PlayerRef): Team?

        fun isTeamFree(team: Team): Boolean
    }
}
