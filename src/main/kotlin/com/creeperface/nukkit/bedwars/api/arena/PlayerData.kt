package com.creeperface.nukkit.bedwars.api.arena

import cn.nukkit.Player

/**
 * Object containing data of playing [Player]
 */
interface PlayerData {

    /**
     * [Arena] instance player is playing int
     */
    val arena: Arena

    /**
     * [Player] instance
     */
    val player: Player

    /**
     *  Player's team instance
     */
    val team: Team
}