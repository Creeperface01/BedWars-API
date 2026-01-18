package com.creeperface.nukkit.bedwars.api.arena.handler

import com.creeperface.nukkit.bedwars.api.arena.PlayerData
import com.creeperface.nukkit.bedwars.api.arena.Team
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration
import com.hypixel.hytale.server.core.universe.PlayerRef

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
