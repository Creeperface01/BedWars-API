package cz.creeperface.hytale.bedwars.api.arena

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.universe.PlayerRef

interface Arena {

    val config: cz.creeperface.hytale.bedwars.api.arena.configuration.ArenaConfiguration

    val players: Map<String, PlayerRef>

    val state: cz.creeperface.hytale.bedwars.api.arena.State<*>

    val context: cz.creeperface.hytale.bedwars.api.utils.ArenaContext

    val closed: Boolean

    fun joinToArena(p: PlayerRef): Boolean

    fun leaveArena(p: PlayerRef)

    fun inArena(p: PlayerRef): Boolean

    /** Broadcast a message to all players in this arena (including spectators). */
    fun broadcastMessage(message: Message)
}
