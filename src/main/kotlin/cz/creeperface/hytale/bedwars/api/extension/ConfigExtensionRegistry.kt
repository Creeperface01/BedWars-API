package cz.creeperface.hytale.bedwars.api.extension

/**
 * Registry for config extension sections.
 *
 * Plugins register their [cz.creeperface.hytale.bedwars.api.extension.ConfigSection] instances here during setup.
 * The configurator module queries this registry to discover extensions
 * and the core module uses it for validation/defaults.
 *
 * Access via [cz.creeperface.hytale.bedwars.api.BedWarsAPI.configExtensions].
 */
interface ConfigExtensionRegistry {

    /**
     * Register a typed config section.
     * @throws IllegalArgumentException if a section with the same [cz.creeperface.hytale.bedwars.api.extension.ConfigSection.id] is already registered
     */
    fun <T : Any> register(section: cz.creeperface.hytale.bedwars.api.extension.ConfigSection<T>)

    /**
     * Get all registered sections for a given target config type.
     * @param targetConfig "arena", "map", or "shop"
     */
    fun getSections(targetConfig: String): List<cz.creeperface.hytale.bedwars.api.extension.ConfigSection<*>>

    /**
     * Get a specific section by ID, or null if not registered.
     */
    fun getSection(id: String): cz.creeperface.hytale.bedwars.api.extension.ConfigSection<*>?
}
