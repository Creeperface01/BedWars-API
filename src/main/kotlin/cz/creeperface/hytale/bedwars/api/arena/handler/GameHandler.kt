package cz.creeperface.hytale.bedwars.api.arena.handler

import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.PlayerData
import cz.creeperface.hytale.bedwars.api.arena.Team
import cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration

interface GameHandler {

    val mapConfig: MapConfiguration

    val teams: List<Team>

    val aliveTeams: List<Team>

    val ending: Boolean

    val winner: Team?

    fun getPlayerData(p: PlayerRef): PlayerData?

    fun getPlayerTeam(p: PlayerRef): Team?

    fun getTeam(id: Int): Team?

    fun isSpectator(p: PlayerRef): Boolean

    interface EndingHandler : GameHandler
}
