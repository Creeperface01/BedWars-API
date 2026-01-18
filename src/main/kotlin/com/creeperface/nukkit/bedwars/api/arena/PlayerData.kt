package com.creeperface.nukkit.bedwars.api.arena

import com.creeperface.nukkit.bedwars.api.data.Stat
import com.hypixel.hytale.server.core.universe.PlayerRef

/**
 * Per-game player data — available while a player is in a game.
 */
interface PlayerData {

    val arena: Arena

    val playerRef: PlayerRef

    val team: Team

    /** Get the session delta for a stat (kills, deaths, etc. this game only). */
    fun getStat(stat: Stat): Int

    /** Whether this player can still respawn (team has bed). */
    fun canRespawn(): Boolean
}
