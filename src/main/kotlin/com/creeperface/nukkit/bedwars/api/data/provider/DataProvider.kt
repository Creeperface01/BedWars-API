package com.creeperface.nukkit.bedwars.api.data.provider

import com.creeperface.nukkit.bedwars.api.arena.configuration.ArenaConfiguration
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration
import com.creeperface.nukkit.bedwars.api.data.Stats
import com.creeperface.nukkit.bedwars.api.shop.ShopConfig

interface DataProvider {

    fun init() {}

    fun deinit() {}

    // ---- Player stats ----

    suspend fun register(name: String, identifier: String)

    suspend fun unregister(identifier: String)

    suspend fun getData(identifier: String): Stats?

    suspend fun getDataByName(name: String): Stats?

    suspend fun saveData(identifier: String, data: Stats)

    // ---- Arena/map config storage ----

    suspend fun loadArenaConfigs(): Map<String, ArenaConfiguration> = emptyMap()

    suspend fun saveArenaConfig(name: String, config: ArenaConfiguration) {}

    suspend fun deleteArenaConfig(name: String) {}

    suspend fun loadMapConfigs(): Map<String, MapConfiguration> = emptyMap()

    suspend fun saveMapConfig(name: String, config: MapConfiguration) {}

    suspend fun deleteMapConfig(name: String) {}

    // ---- Shop config storage ----

    suspend fun loadShopConfigs(): Map<String, ShopConfig> = emptyMap()

    suspend fun saveShopConfig(name: String, config: ShopConfig) {}

    suspend fun deleteShopConfig(name: String) {}
}
