package cz.creeperface.hytale.bedwars.api.event

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
    arena: cz.creeperface.hytale.bedwars.api.arena.Arena,
    val victim: cz.creeperface.hytale.bedwars.api.event.ArenaParticipant,
    val killer: cz.creeperface.hytale.bedwars.api.event.ArenaParticipant
) : cz.creeperface.hytale.bedwars.api.event.ArenaEvent(arena)
