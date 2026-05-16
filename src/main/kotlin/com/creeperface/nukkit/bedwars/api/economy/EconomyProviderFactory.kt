package com.creeperface.nukkit.bedwars.api.economy

/**
 * Factory for creating an [EconomyProvider] instance.
 *
 * Register via [com.creeperface.nukkit.bedwars.api.BedWarsAPI.registerEconomyProvider].
 * The factory is invoked when the configured economy provider name matches the registered key.
 * Implementations are responsible for verifying their dependencies (e.g. presence of an economy plugin)
 * and may return a fallback provider if unavailable.
 */
fun interface EconomyProviderFactory {
    fun create(): EconomyProvider
}
