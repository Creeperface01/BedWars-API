package cz.creeperface.hytale.bedwars.api.data.provider

import cz.creeperface.hytale.bedwars.api.arena.configuration.ArenaConfiguration
import cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration
import cz.creeperface.hytale.bedwars.api.data.Stats
import cz.creeperface.hytale.bedwars.api.shop.ShopConfig
import java.util.concurrent.CompletableFuture

interface DataProvider {

    fun init() {}

    fun deinit() {}

    // ---- Player stats ----

    fun register(name: String, identifier: String): CompletableFuture<Void>

    fun unregister(identifier: String): CompletableFuture<Void>

    fun getData(identifier: String): CompletableFuture<Stats?>

    fun getDataByName(name: String): CompletableFuture<Stats?>

    fun saveData(identifier: String, data: Stats): CompletableFuture<Void>

    // ---- Arena/map config storage ----

    fun loadArenaConfigs(): CompletableFuture<Map<String, ArenaConfiguration>>

    fun saveArenaConfig(
        name: String,
        config: ArenaConfiguration
    ): CompletableFuture<Void>

    fun deleteArenaConfig(name: String): CompletableFuture<Void>

    fun loadMapConfigs(): CompletableFuture<Map<String, MapConfiguration>>

    fun saveMapConfig(
        name: String,
        config: MapConfiguration
    ): CompletableFuture<Void>

    fun deleteMapConfig(name: String): CompletableFuture<Void>

    // ---- Shop config storage ----

    fun loadShopConfigs(): CompletableFuture<Map<String, ShopConfig>>

    fun saveShopConfig(name: String, config: ShopConfig): CompletableFuture<Void>

    fun deleteShopConfig(name: String): CompletableFuture<Void>
}
