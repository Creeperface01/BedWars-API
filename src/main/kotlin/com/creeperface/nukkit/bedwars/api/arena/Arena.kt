package com.creeperface.nukkit.bedwars.api.arena

import com.creeperface.nukkit.bedwars.api.arena.configuration.ArenaConfiguration
import com.creeperface.nukkit.bedwars.api.utils.ArenaContext
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.universe.PlayerRef

interface Arena {

    val config: ArenaConfiguration

    val players: Map<String, PlayerRef>

    val state: State<*>

    val context: ArenaContext

    val closed: Boolean

    fun joinToArena(p: PlayerRef): Boolean

    fun leaveArena(p: PlayerRef)

    fun inArena(p: PlayerRef): Boolean

    /** Broadcast a message to all players in this arena (including spectators). */
    fun broadcastMessage(message: Message)
}
