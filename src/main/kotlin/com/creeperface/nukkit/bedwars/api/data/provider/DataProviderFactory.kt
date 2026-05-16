package com.creeperface.nukkit.bedwars.api.data.provider

/**
 * Factory for creating a [DataProvider] instance.
 *
 * Register via [com.creeperface.nukkit.bedwars.api.BedWarsAPI.registerDataProvider].
 * The factory is invoked when the configured data provider name matches the registered key.
 * Implementations are responsible for loading their own configuration.
 */
fun interface DataProviderFactory {
    fun create(): DataProvider
}
