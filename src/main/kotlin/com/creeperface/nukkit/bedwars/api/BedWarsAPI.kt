package com.creeperface.nukkit.bedwars.api

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.data.provider.DataProvider
import com.creeperface.nukkit.bedwars.api.data.provider.DataProviderFactory
import com.creeperface.nukkit.bedwars.api.economy.EconomyProvider
import com.creeperface.nukkit.bedwars.api.economy.EconomyProviderFactory
import com.creeperface.nukkit.bedwars.api.extension.ConfigExtensionRegistry
import com.hypixel.hytale.server.core.universe.PlayerRef

interface BedWarsAPI {

    val economyProvider: EconomyProvider

    val dataProvider: DataProvider

    /** Registry for config extension sections and UI extension points. */
    val configExtensions: ConfigExtensionRegistry

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

    /**
     * Register an economy provider factory under a name. The name is matched
     * against the `economy_provider` setting in `economy.json` (case-insensitive).
     *
     * Must be called before the BedWars plugin resolves its providers — register
     * from your plugin's `setup()` (and ensure your plugin loads before BedWars).
     */
    fun registerEconomyProvider(name: String, factory: EconomyProviderFactory)

    /**
     * Register a data provider factory under a name. The name is matched against
     * the `data_provider` setting in `config.json` (case-insensitive).
     *
     * Must be called before the BedWars plugin resolves its providers — register
     * from your plugin's `setup()` (and ensure your plugin loads before BedWars).
     */
    fun registerDataProvider(name: String, factory: DataProviderFactory)

    companion object {

        private var _instance: BedWarsAPI? = null

        @JvmStatic
        val instance: BedWarsAPI
            get() = _instance ?: throw IllegalStateException("BedWars API not initialized")

        /** Set by the core plugin during setup. Not intended for external use. */
        fun setInstance(api: BedWarsAPI) {
            require(_instance == null) { "BedWars API already initialized" }
            _instance = api
        }
    }
}
