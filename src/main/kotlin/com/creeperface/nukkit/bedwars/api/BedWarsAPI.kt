package com.creeperface.nukkit.bedwars.api

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.data.provider.DataProvider
import com.creeperface.nukkit.bedwars.api.economy.EconomyProvider
import com.creeperface.nukkit.bedwars.api.shop.ShopExtension
import com.creeperface.nukkit.bedwars.api.special.SpecialItemRegistry
import com.hypixel.hytale.server.core.universe.PlayerRef
import kotlin.reflect.KClass

interface BedWarsAPI {

    val economyProvider: EconomyProvider

    val dataProvider: DataProvider

    /** Registry for custom special items (fireballs, bridge eggs, etc.). */
    val specialItems: SpecialItemRegistry

    /** API for dynamically extending the shop from other plugins. */
    val shopExtension: ShopExtension

    /** Get all active arenas. */
    fun getArenas(): Collection<Arena>

    /** Get arena by name. */
    fun getArena(arena: String): Arena?

    /** Get the arena a player is currently in. */
    fun getPlayerArena(p: PlayerRef): Arena?

    /** Join the best available arena. */
    fun joinRandomArena(p: PlayerRef)

    /** Find a free arena for the player. */
    fun getFreeArena(p: PlayerRef): Arena?

    fun registerEconomyProvider(name: String, provider: KClass<out EconomyProvider>)

    fun registerDataProvider(name: String, provider: KClass<out DataProvider>)

    companion object {

        private var _instance: BedWarsAPI? = null

        val instance: BedWarsAPI
            get() = _instance ?: throw IllegalStateException("BedWars API not initialized")

        /** Set by the core plugin during setup. Not intended for external use. */
        fun setInstance(api: BedWarsAPI) {
            _instance = api
        }
    }
}
