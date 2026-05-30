package cz.creeperface.hytale.bedwars.api.arena

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.configuration.ArenaConfiguration
import cz.creeperface.hytale.bedwars.api.utils.ArenaContext

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
