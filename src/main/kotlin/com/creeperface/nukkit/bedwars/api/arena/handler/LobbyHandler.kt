package com.creeperface.nukkit.bedwars.api.arena.handler

import cn.nukkit.Player
import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.Team
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration

interface LobbyHandler : Arena {

    interface VotingHandler : LobbyHandler {

    }

    interface TeamSelectHandler : LobbyHandler {

        val teams: List<Team>

        val starting: Boolean

        val mapConfig: MapConfiguration

        fun getPlayerTeam(p: Player): Team?

        /**
         * Returns true if the team isn't full and other teams are filled enough
         * Return value is based on difference between [team] actual players
         * and a team with minimal player count
         */
        fun isTeamFree(team: Team): Boolean
    }
}