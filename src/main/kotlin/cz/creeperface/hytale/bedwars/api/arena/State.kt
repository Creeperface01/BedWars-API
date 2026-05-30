package cz.creeperface.hytale.bedwars.api.arena

import cz.creeperface.hytale.bedwars.api.arena.handler.GameHandler
import cz.creeperface.hytale.bedwars.api.arena.handler.LobbyHandler.TeamSelectHandler
import cz.creeperface.hytale.bedwars.api.arena.handler.LobbyHandler.VotingHandler

/**
 * Represents the current state of an arena.
 * Each state holds a typed handler that manages that phase's logic.
 */
sealed class State<out T> {

    object Waiting : State<Nothing>()

    data class Voting(val handler: VotingHandler) :
        State<VotingHandler>()

    data class TeamSelect(val handler: TeamSelectHandler) :
        State<TeamSelectHandler>()

    data class Game(val handler: GameHandler) :
        State<GameHandler>()
}
