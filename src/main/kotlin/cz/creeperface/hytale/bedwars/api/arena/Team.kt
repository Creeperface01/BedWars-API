package cz.creeperface.hytale.bedwars.api.arena

import com.hypixel.hytale.server.core.Message

/**
 * Represents a game team at runtime.
 */
interface Team {

    val id: Int

    val arena: cz.creeperface.hytale.bedwars.api.arena.Arena

    val config: cz.creeperface.hytale.bedwars.api.arena.configuration.TeamConfiguration

    val context: cz.creeperface.hytale.bedwars.api.utils.TeamContext

    val playerCount: Int

    fun hasBed(): Boolean

    fun isAlive(): Boolean

    /** Programmatically destroy this team's bed. */
    fun destroyBed()

    fun messagePlayers(message: String)

    fun messagePlayers(message: Message)

    fun getTeamPlayers(): Map<String, cz.creeperface.hytale.bedwars.api.arena.PlayerData>
}
