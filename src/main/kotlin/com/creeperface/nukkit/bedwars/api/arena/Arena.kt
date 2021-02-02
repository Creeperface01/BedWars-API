package com.creeperface.nukkit.bedwars.api.arena

import cn.nukkit.Player
import com.creeperface.nukkit.bedwars.api.arena.configuration.IArenaConfiguration
import com.creeperface.nukkit.bedwars.api.utils.ArenaContext
import com.creeperface.nukkit.bedwars.api.utils.Lang

interface Arena : IArenaConfiguration {

    val players: Map<String, Player>

    val state: State<*>

    val context: ArenaContext

    val closed: Boolean

    fun getHandler(): Arena

    fun joinToArena(p: Player): Boolean

    fun leaveArena(p: Player)

    fun inArena(p: Player): Boolean

    fun messageAllPlayers(lang: Lang, vararg args: String)

    /**
     * Messages all players in this [Arena]
     * This method includes spectators
     */
    fun messageAllPlayers(lang: Lang, addPrefix: Boolean = false, vararg args: String)
}