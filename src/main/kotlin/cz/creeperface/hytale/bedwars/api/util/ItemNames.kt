package cz.creeperface.hytale.bedwars.api.util

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.asset.type.item.config.Item
import com.hypixel.hytale.server.core.ui.LocalizableString

/**
 * Resolves Hytale item ids to localized, translation-key-backed display names.
 *
 * Translation is client-side: we send a translation KEY and the player's client renders it
 * in their language. We use each item asset's own declared name key
 * (`translationProperties.name`, e.g. `items.Deco_Map.name`). When an item has no asset or
 * declares no name key, we fall back to the raw id — note that `Item.getTranslationKey()`
 * would instead fabricate a non-resolving `server.items.<id>.name`, so we deliberately do
 * not use it.
 */
object ItemNames {

    /** Translation-key-backed label for dropdowns / UI labels. Falls back to the raw id. */
    fun label(itemId: String): LocalizableString =
        nameKey(itemId)?.let { LocalizableString.fromMessageId(it) }
            ?: LocalizableString.fromString(itemId)

    /** Override-aware label: a non-blank custom name wins, else translated name, else raw id. */
    fun label(itemId: String, override: String?): LocalizableString =
        if (!override.isNullOrBlank()) LocalizableString.fromString(override) else label(itemId)

    /**
     * Translation-key-backed Message for the item name (a single `Message.translation(key)`, or the
     * raw id when unknown). Use it as a STANDALONE UI label (the client resolves a lone MessageId),
     * or as a chat `.param(...)` value (chat substitutes message params).
     *
     * Param-free on purpose (NOT `item.translationMessage`). Two client limits drive this: a UI
     * `label.text` must be a single scalar Message (a `Message.join`/`Children` composite crashes the
     * client), and a UI label does NOT substitute message-valued params (`{item}` stays literal). So
     * to show "name + literal text" in the UI, render the name and the text as SEPARATE labels.
     */
    fun message(itemId: String): Message =
        nameKey(itemId)?.let { Message.translation(it) } ?: Message.raw(itemId)

    /** Override-aware Message: a non-blank custom name wins, else translated name, else raw id. */
    fun message(itemId: String, override: String?): Message =
        if (!override.isNullOrBlank()) Message.raw(override) else message(itemId)

    private fun nameKey(itemId: String): String? =
        if (itemId.isBlank()) null
        else Item.getAssetMap().getAsset(itemId)?.translationProperties?.name
}
