package cz.creeperface.hytale.bedwars.api.event

import com.hypixel.hytale.server.core.universe.PlayerRef
import java.util.*

/**
 * Identifies a participant in an arena event — either a human player or an AI bot.
 *
 * Used by events where the actor can be either type (e.g. [cz.creeperface.hytale.bedwars.api.event.ArenaKillEvent]).
 * Listeners interested in only one variant should pattern-match with `is`.
 */
sealed class ArenaParticipant {

    /** Team this participant is on at the time the event fires. */
    abstract val team: cz.creeperface.hytale.bedwars.api.arena.Team

    /** Human-readable name for chat / log output. */
    abstract val displayName: String

    /** A human player. */
    data class Player(
        val playerRef: PlayerRef,
        override val team: cz.creeperface.hytale.bedwars.api.arena.Team
    ) : ArenaParticipant() {
        override val displayName: String get() = playerRef.username
    }

    /** An AI-controlled bot. */
    data class Bot(
        val uuid: UUID,
        val name: String,
        override val team: cz.creeperface.hytale.bedwars.api.arena.Team
    ) : ArenaParticipant() {
        override val displayName: String get() = name
    }
}
