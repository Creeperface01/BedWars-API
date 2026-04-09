package com.creeperface.nukkit.bedwars.api.extension

/**
 * A named slot in the configurator UI where plugins can inject custom elements.
 *
 * Plugins register handlers that receive a typed [context][C] providing
 * access to the UI builder, current config data, and save callbacks.
 *
 * The context objects contain a `builder` field typed as the UiManager's
 * `ChildNodeBuilder`, giving full access to the UiManager DSL.
 *
 * Example:
 * ```
 * UiExtensionPoints.MAP_SETUP_BUTTONS.register { ctx ->
 *     ctx.builder.group {
 *         defaultTextButton {
 *             text = "Manage Kits".translated()
 *             onActivate { showKitsPage(ctx.playerRef) }
 *         }
 *     }
 * }
 * ```
 *
 * @param C The context type for this extension point
 * @param id Unique identifier for this extension point
 */
class UiExtensionPoint<C>(val id: String) {

    private val handlers = mutableListOf<(C) -> Unit>()

    /**
     * Register a handler that will be called when this extension point is rendered.
     * Handlers are called in registration order.
     */
    fun register(handler: (C) -> Unit) {
        handlers.add(handler)
    }

    /**
     * Invoke all registered handlers with the given context.
     * Called by the configurator module when rendering the page.
     */
    fun render(context: C) {
        for (handler in handlers) {
            handler(context)
        }
    }

    /**
     * Check if any handlers are registered.
     */
    fun hasHandlers(): Boolean = handlers.isNotEmpty()
}
