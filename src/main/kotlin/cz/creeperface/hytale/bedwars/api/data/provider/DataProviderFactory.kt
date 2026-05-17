package cz.creeperface.hytale.bedwars.api.data.provider

/**
 * Factory for creating a [cz.creeperface.hytale.bedwars.api.data.provider.DataProvider] instance.
 *
 * Register via [cz.creeperface.hytale.bedwars.api.BedWarsAPI.registerDataProvider].
 * The factory is invoked when the configured data provider name matches the registered key.
 * Implementations are responsible for loading their own configuration.
 */
fun interface DataProviderFactory {
    fun create(): cz.creeperface.hytale.bedwars.api.data.provider.DataProvider
}
