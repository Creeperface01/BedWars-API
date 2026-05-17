package cz.creeperface.hytale.bedwars.api.arena

import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Per-game player data — available while a player is in a game.
 */
interface PlayerData {

    val arena: cz.creeperface.hytale.bedwars.api.arena.Arena

    val playerRef: PlayerRef

    val team: cz.creeperface.hytale.bedwars.api.arena.Team

    /** Get the session delta for a stat (kills, deaths, etc. this game only). */
    fun getStat(stat: cz.creeperface.hytale.bedwars.api.data.Stat): Int

    /** Whether this player can still respawn (team has bed). */
    fun canRespawn(): Boolean
}
