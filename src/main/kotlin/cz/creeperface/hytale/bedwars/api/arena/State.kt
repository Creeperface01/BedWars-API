package cz.creeperface.hytale.bedwars.api.arena

/**
 * Represents the current state of an arena.
 * Each state holds a typed handler that manages that phase's logic.
 */
sealed class State<out T> {

    object Waiting : State<Nothing>()

    data class Voting(val handler: cz.creeperface.hytale.bedwars.api.arena.handler.LobbyHandler.VotingHandler) :
        State<cz.creeperface.hytale.bedwars.api.arena.handler.LobbyHandler.VotingHandler>()

    data class TeamSelect(val handler: cz.creeperface.hytale.bedwars.api.arena.handler.LobbyHandler.TeamSelectHandler) :
        State<cz.creeperface.hytale.bedwars.api.arena.handler.LobbyHandler.TeamSelectHandler>()

    data class Game(val handler: cz.creeperface.hytale.bedwars.api.arena.handler.GameHandler) :
        State<cz.creeperface.hytale.bedwars.api.arena.handler.GameHandler>()
}
