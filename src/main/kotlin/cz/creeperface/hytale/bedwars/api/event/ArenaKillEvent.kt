package cz.creeperface.hytale.bedwars.api.event

import cz.creeperface.hytale.bedwars.api.arena.Arena

/**
 * Fired when a participant kills another participant in an arena.
 *
 * Both [victim] and [killer] can be a human player or an AI bot — see
 * [cz.creeperface.hytale.bedwars.api.event.ArenaParticipant]. Covers all four directional combinations:
 * player→player, player→bot, bot→player, bot→bot.
 *
 * Not fired for environmental deaths (fall damage, void) where no killer
 * can be attributed.
 */
class ArenaKillEvent(
    arena: Arena,
    val victim: ArenaParticipant,
    val killer: ArenaParticipant
) : ArenaEvent(arena)
