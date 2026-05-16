package com.creeperface.nukkit.bedwars.api.event

import com.creeperface.nukkit.bedwars.api.arena.Team
import com.hypixel.hytale.server.core.universe.PlayerRef
import java.util.*

/**
 * Identifies a participant in an arena event — either a human player or an AI bot.
 *
 * Used by events where the actor can be either type (e.g. [ArenaKillEvent]).
 * Listeners interested in only one variant should pattern-match with `is`.
 */
sealed class ArenaParticipant {

    /** Team this participant is on at the time the event fires. */
    abstract val team: Team

    /** Human-readable name for chat / log output. */
    abstract val displayName: String

    /** A human player. */
    data class Player(
        val playerRef: PlayerRef,
        override val team: Team
    ) : ArenaParticipant() {
        override val displayName: String get() = playerRef.username
    }

    /** An AI-controlled bot. */
    data class Bot(
        val uuid: UUID,
        val name: String,
        override val team: Team
    ) : ArenaParticipant() {
        override val displayName: String get() = name
    }
}
