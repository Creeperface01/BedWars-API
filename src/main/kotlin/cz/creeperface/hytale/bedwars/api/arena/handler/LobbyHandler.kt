package cz.creeperface.hytale.bedwars.api.arena.handler

import com.hypixel.hytale.server.core.universe.PlayerRef

interface LobbyHandler {

    interface VotingHandler : LobbyHandler

    interface TeamSelectHandler : LobbyHandler {

        val teams: List<cz.creeperface.hytale.bedwars.api.arena.Team>

        val starting: Boolean

        val mapConfig: cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration

        fun getPlayerTeam(p: PlayerRef): cz.creeperface.hytale.bedwars.api.arena.Team?

        fun isTeamFree(team: cz.creeperface.hytale.bedwars.api.arena.Team): Boolean
    }
}
