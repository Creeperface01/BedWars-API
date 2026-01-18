package com.creeperface.nukkit.bedwars.api.arena

import com.creeperface.nukkit.bedwars.api.arena.handler.GameHandler
import com.creeperface.nukkit.bedwars.api.arena.handler.LobbyHandler

/**
 * Represents the current state of an arena.
 * Each state holds a typed handler that manages that phase's logic.
 */
sealed class State<out T> {

    object Waiting : State<Nothing>()

    data class Voting(val handler: LobbyHandler.VotingHandler) : State<LobbyHandler.VotingHandler>()

    data class TeamSelect(val handler: LobbyHandler.TeamSelectHandler) : State<LobbyHandler.TeamSelectHandler>()

    data class Game(val handler: GameHandler) : State<GameHandler>()
}
