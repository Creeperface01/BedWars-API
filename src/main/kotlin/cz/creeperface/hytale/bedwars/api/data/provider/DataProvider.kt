package cz.creeperface.hytale.bedwars.api.data.provider

import java.util.concurrent.CompletableFuture

interface DataProvider {

    fun init() {}

    fun deinit() {}

    // ---- Player stats ----

    fun register(name: String, identifier: String): CompletableFuture<Void>

    fun unregister(identifier: String): CompletableFuture<Void>

    fun getData(identifier: String): CompletableFuture<cz.creeperface.hytale.bedwars.api.data.Stats?>

    fun getDataByName(name: String): CompletableFuture<cz.creeperface.hytale.bedwars.api.data.Stats?>

    fun saveData(identifier: String, data: cz.creeperface.hytale.bedwars.api.data.Stats): CompletableFuture<Void>

    // ---- Arena/map config storage ----

    fun loadArenaConfigs(): CompletableFuture<Map<String, cz.creeperface.hytale.bedwars.api.arena.configuration.ArenaConfiguration>>

    fun saveArenaConfig(
        name: String,
        config: cz.creeperface.hytale.bedwars.api.arena.configuration.ArenaConfiguration
    ): CompletableFuture<Void>

    fun deleteArenaConfig(name: String): CompletableFuture<Void>

    fun loadMapConfigs(): CompletableFuture<Map<String, cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration>>

    fun saveMapConfig(
        name: String,
        config: cz.creeperface.hytale.bedwars.api.arena.configuration.MapConfiguration
    ): CompletableFuture<Void>

    fun deleteMapConfig(name: String): CompletableFuture<Void>

    // ---- Shop config storage ----

    fun loadShopConfigs(): CompletableFuture<Map<String, cz.creeperface.hytale.bedwars.api.shop.ShopConfig>>

    fun saveShopConfig(name: String, config: cz.creeperface.hytale.bedwars.api.shop.ShopConfig): CompletableFuture<Void>

    fun deleteShopConfig(name: String): CompletableFuture<Void>
}
