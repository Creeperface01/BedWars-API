package cz.creeperface.hytale.bedwars.api.economy

/**
 * Factory for creating an [cz.creeperface.hytale.bedwars.api.economy.EconomyProvider] instance.
 *
 * Register via [cz.creeperface.hytale.bedwars.api.BedWarsAPI.registerEconomyProvider].
 * The factory is invoked when the configured economy provider name matches the registered key.
 * Implementations are responsible for verifying their dependencies (e.g. presence of an economy plugin)
 * and may return a fallback provider if unavailable.
 */
fun interface EconomyProviderFactory {
    fun create(): cz.creeperface.hytale.bedwars.api.economy.EconomyProvider
}
