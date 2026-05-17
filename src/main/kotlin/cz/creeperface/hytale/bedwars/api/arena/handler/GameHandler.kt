package cz.creeperface.hytale.bedwars.api.arena.handler

import com.hypixel.hytale.server.core.universe.PlayerRef

interface GameHandler {

    val mapConfig: cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration

    val teams: List<cz.creeperface.hytale.bedwars.api.arena.Team>

    val aliveTeams: List<cz.creeperface.hytale.bedwars.api.arena.Team>

    val ending: Boolean

    val winner: cz.creeperface.hytale.bedwars.api.arena.Team?

    fun getPlayerData(p: PlayerRef): cz.creeperface.hytale.bedwars.api.arena.PlayerData?

    fun getPlayerTeam(p: PlayerRef): cz.creeperface.hytale.bedwars.api.arena.Team?

    fun getTeam(id: Int): cz.creeperface.hytale.bedwars.api.arena.Team?

    fun isSpectator(p: PlayerRef): Boolean

    interface EndingHandler : GameHandler
}
