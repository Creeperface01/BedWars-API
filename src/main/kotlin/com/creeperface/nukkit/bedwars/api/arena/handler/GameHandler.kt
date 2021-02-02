package com.creeperface.nukkit.bedwars.api.arena.handler

import cn.nukkit.Player
import cn.nukkit.level.Level
import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.PlayerData
import com.creeperface.nukkit.bedwars.api.arena.Team
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration
import com.creeperface.nukkit.bedwars.api.utils.Lang

interface GameHandler : Arena {

    val spectators: Map<String, Player>

    val aliveTeams: List<Team>

    val mapConfig: MapConfiguration

    val teams: List<Team>

    val level: Level

    val ending: Boolean

    val winner: Team?

    fun dropBronze()

    fun dropIron()

    fun dropGold()

    fun getPlayerData(p: Player): PlayerData?

    fun getPlayerTeam(p: Player): Team?

    fun getTeam(id: Int): Team?

    fun isSpectator(p: Player): Boolean

    fun setSpectator(p: Player)

    fun messageGamePlayers(lang: Lang, vararg args: String)

    /**
     * Messages all playing players in this [Arena]
     * This method doesn't include spectating players
     */
    fun messageGamePlayers(lang: Lang, addPrefix: Boolean, vararg args: String)

    interface EndingHandler : GameHandler
}