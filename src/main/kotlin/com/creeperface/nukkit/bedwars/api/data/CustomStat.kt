package com.creeperface.nukkit.bedwars.api.data

/**
 * A custom stat that can be registered by third-party plugins.
 * Custom stats are tracked per-player alongside built-in [Stat] values.
 */
data class CustomStat(
    val id: String,
    val displayName: String
) {

    companion object {
        private val registry = mutableMapOf<String, CustomStat>()

        /** Register a custom stat. */
        fun register(stat: CustomStat) {
            registry[stat.id] = stat
        }

        /** Get a custom stat by ID. */
        fun get(id: String): CustomStat? = registry[id]

        /** Get all registered custom stats. */
        fun getAll(): Collection<CustomStat> = registry.values
    }
}
