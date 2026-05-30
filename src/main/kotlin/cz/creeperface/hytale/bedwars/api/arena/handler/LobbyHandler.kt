package cz.creeperface.hytale.bedwars.api.arena.handler

import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.Team
import cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration

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
