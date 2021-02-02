package com.creeperface.nukkit.bedwars.api.arena

import cn.nukkit.inventory.Inventory
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration
import com.creeperface.nukkit.bedwars.api.shop.ShopMenuWindow
import com.creeperface.nukkit.bedwars.api.utils.TeamContext

/**
 *  [Team] class represents game team
 */
interface Team : MapConfiguration.ITeamData {

    /**
     * [Team] numeric ID starting from 0
     */
    val id: Int

    /**
     * Actual [Arena] instance
     */
    val arena: Arena

    /**
     * [Team]'s ender chest [Inventory]
     */
    val enderChest: Inventory

    val shop: ShopMenuWindow

    /**
     * PlaceholderAPI scope context instance
     * Used for loading arena/team related configurations or in game chat format
     */
    val context: TeamContext

    /**
     * Returns true when bed of this team hasn't been destroyed yet
     */
    fun hasBed(): Boolean

    /**
     * Returns true if team's bed still remains undestroyed
     * or if at least one player in this team is still alive
     */
    fun isAlive(): Boolean

    /**
     * Sends message to all players in this team
     */
    fun messagePlayers(message: String)

    /**
     * Returns copy of current team players
     */
    fun getTeamPlayers(): Map<String, PlayerData>
}