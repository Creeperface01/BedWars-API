package com.creeperface.nukkit.bedwars.api.extension

/**
 * Registry for config extension sections.
 *
 * Plugins register their [ConfigSection] instances here during setup.
 * The configurator module queries this registry to discover extensions
 * and the core module uses it for validation/defaults.
 *
 * Access via [com.creeperface.nukkit.bedwars.api.BedWarsAPI.configExtensions].
 */
interface ConfigExtensionRegistry {

    /**
     * Register a typed config section.
     * @throws IllegalArgumentException if a section with the same [ConfigSection.id] is already registered
     */
    fun <T : Any> register(section: ConfigSection<T>)

    /**
     * Get all registered sections for a given target config type.
     * @param targetConfig "arena", "map", or "shop"
     */
    fun getSections(targetConfig: String): List<ConfigSection<*>>

    /**
     * Get a specific section by ID, or null if not registered.
     */
    fun getSection(id: String): ConfigSection<*>?
}
