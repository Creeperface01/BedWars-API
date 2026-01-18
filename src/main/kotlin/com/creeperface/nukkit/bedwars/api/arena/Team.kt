package com.creeperface.nukkit.bedwars.api.arena

import com.creeperface.nukkit.bedwars.api.arena.configuration.TeamConfiguration
import com.creeperface.nukkit.bedwars.api.utils.TeamContext
import com.hypixel.hytale.server.core.Message

/**
 * Represents a game team at runtime.
 */
interface Team {

    val id: Int

    val arena: Arena

    val config: TeamConfiguration

    val context: TeamContext

    val playerCount: Int

    fun hasBed(): Boolean

    fun isAlive(): Boolean

    /** Programmatically destroy this team's bed. */
    fun destroyBed()

    fun messagePlayers(message: String)

    fun messagePlayers(message: Message)

    fun getTeamPlayers(): Map<String, PlayerData>
}
