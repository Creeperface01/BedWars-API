package com.creeperface.nukkit.bedwars.api.arena

import com.creeperface.nukkit.bedwars.api.arena.handler.GameHandler
import com.creeperface.nukkit.bedwars.api.arena.handler.LobbyHandler

sealed class State<T : Arena>

object VOTING : State<LobbyHandler.VotingHandler>()
object TEAM_SELECT : State<LobbyHandler.TeamSelectHandler>()
object GAME : State<GameHandler>()
