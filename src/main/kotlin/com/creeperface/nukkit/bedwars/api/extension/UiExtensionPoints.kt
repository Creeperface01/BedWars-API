package com.creeperface.nukkit.bedwars.api.extension

import com.hypixel.hytale.server.core.universe.PlayerRef
import kotlinx.serialization.json.JsonObject

/**
 * Predefined UI extension points in the BedWars configurator.
 *
 * Each extension point represents a slot in a configurator page where plugins
 * can inject custom UI elements (buttons, form fields, sections, etc.).
 *
 * The `builder` field in each context is a UiManager `ChildNodeBuilder`.
 * Cast it to use the UiManager DSL:
 * ```
 * UiExtensionPoints.MAP_SETUP_BUTTONS.register { ctx ->
 *     (ctx.builder as ChildNodeBuilder).group {
 *         defaultTextButton { ... }
 *     }
 * }
 * ```
 *
 * If your plugin already depends on UiManager (which it should for UI work),
 * use the typed helper extensions provided by the configurator API.
 */
object UiExtensionPoints {

    /** Main configurator page — add buttons alongside Arenas / Maps / Shops. */
    val MAIN_PAGE_BUTTONS = UiExtensionPoint<MainPageContext>("bedwars:main_page_buttons")

    /** Arena setup page — inject sections before the Save/Discard buttons. */
    val ARENA_SETUP_SECTIONS = UiExtensionPoint<ArenaEditContext>("bedwars:arena_setup_sections")

    /** Map setup page — add buttons alongside Teams / Resources. */
    val MAP_SETUP_BUTTONS = UiExtensionPoint<MapEditContext>("bedwars:map_setup_buttons")

    /** Map setup page — inject sections before the Save/Discard buttons. */
    val MAP_SETUP_SECTIONS = UiExtensionPoint<MapEditContext>("bedwars:map_setup_sections")

    /** Shop setup (category list) page — inject sections before Save/Discard. */
    val SHOP_SETUP_SECTIONS = UiExtensionPoint<ShopEditContext>("bedwars:shop_setup_sections")
}

// ── Extension point context types ──

/**
 * Context for main configurator page extension points.
 *
 * @property builder UiManager ChildNodeBuilder — cast to use DSL
 * @property playerRef The player viewing the configurator
 */
data class MainPageContext(
    val builder: Any,
    val playerRef: PlayerRef
)

/**
 * Context for arena setup page extension points.
 *
 * @property builder UiManager ChildNodeBuilder — cast to use DSL
 * @property playerRef The player editing the arena
 * @property extensions Current extension data from the arena draft
 * @property updateExtensions Callback to update extension data. Pass a function
 *   that receives the current JsonObject and returns the updated one.
 *   The draft is auto-persisted after the callback.
 */
data class ArenaEditContext(
    val builder: Any,
    val playerRef: PlayerRef,
    val extensions: JsonObject,
    val updateExtensions: ((JsonObject) -> JsonObject) -> Unit
)

/**
 * Context for map setup page extension points.
 *
 * @property builder UiManager ChildNodeBuilder — cast to use DSL
 * @property playerRef The player editing the map
 * @property extensions Current extension data from the map draft
 * @property updateExtensions Callback to update extension data
 */
data class MapEditContext(
    val builder: Any,
    val playerRef: PlayerRef,
    val extensions: JsonObject,
    val updateExtensions: ((JsonObject) -> JsonObject) -> Unit
)

/**
 * Context for shop setup page extension points.
 *
 * @property builder UiManager ChildNodeBuilder — cast to use DSL
 * @property playerRef The player editing the shop
 * @property extensions Current extension data from the shop draft
 * @property updateExtensions Callback to update extension data
 */
data class ShopEditContext(
    val builder: Any,
    val playerRef: PlayerRef,
    val extensions: JsonObject,
    val updateExtensions: ((JsonObject) -> JsonObject) -> Unit
)
