package cz.creeperface.hytale.bedwars.api.extension

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

/**
 * Defines a typed config section that can be attached to arena, map, or shop configs.
 *
 * Plugins create instances of this class to register their custom configuration data.
 * The data is stored in the parent config's `extensions` JsonObject under the [id] key
 * and accessed type-safely through [getFrom] / [setIn].
 *
 * Example:
 * ```
 * @Serializable
 * data class KitsConfig(val enabled: Boolean = false, val maxLevel: Int = 3)
 *
 * val KITS = ConfigSection(
 *     id = "myplugin:kits",
 *     targetConfig = "map",
 *     serializer = KitsConfig.serializer(),
 *     default = KitsConfig()
 * )
 *
 * // Read from a map config
 * val kits: KitsConfig = KITS.getFrom(mapConfig.extensions)
 *
 * // Write to a map config
 * val updated = KITS.setIn(mapConfig.extensions, kits.copy(enabled = true))
 * ```
 *
 * @param T The data class type for this config section
 * @param id Unique identifier, should be namespaced (e.g. "myplugin:kits")
 * @param targetConfig Which config this section attaches to: "arena", "map", or "shop"
 * @param serializer Kotlinx serialization serializer for [T]
 * @param default Default value when the section is missing from the config
 */
class ConfigSection<T : Any>(
    val id: String,
    val targetConfig: String,
    val serializer: KSerializer<T>,
    val default: T
) {

    /**
     * Read this section's data from an extensions JsonObject.
     * Returns [default] if the section is not present.
     */
    fun getFrom(extensions: JsonObject): T {
        val element = extensions[id] ?: return default
        return try {
            JSON.decodeFromJsonElement(serializer, element)
        } catch (_: Exception) {
            default
        }
    }

    /**
     * Write this section's data into an extensions JsonObject.
     * Returns a new JsonObject with the section added/replaced.
     */
    fun setIn(extensions: JsonObject, value: T): JsonObject {
        val element = JSON.encodeToJsonElement(serializer, value)
        return JsonObject(extensions + (id to element))
    }

    /**
     * Remove this section from an extensions JsonObject.
     * Returns a new JsonObject without the section.
     */
    fun removeFrom(extensions: JsonObject): JsonObject {
        return JsonObject(extensions - id)
    }

    companion object {
        internal val JSON = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }
    }
}

/**
 * Convenience extension to read a [cz.creeperface.hytale.bedwars.api.extension.ConfigSection] from this JsonObject.
 */
fun <T : Any> JsonObject.getSection(section: cz.creeperface.hytale.bedwars.api.extension.ConfigSection<T>): T =
    section.getFrom(this)

/**
 * Convenience extension to write a [cz.creeperface.hytale.bedwars.api.extension.ConfigSection] into this JsonObject.
 * Returns a new JsonObject with the section data.
 */
fun <T : Any> JsonObject.withSection(
    section: cz.creeperface.hytale.bedwars.api.extension.ConfigSection<T>,
    value: T
): JsonObject = section.setIn(this, value)
