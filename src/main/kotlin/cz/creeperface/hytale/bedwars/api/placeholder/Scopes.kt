package cz.creeperface.hytale.bedwars.api.placeholder

import cz.creeperface.hytale.placeholderapi.api.scope.Scope

object ArenaScope : Scope<cz.creeperface.hytale.bedwars.api.arena.Arena, ArenaScope>()

//object ArenaGameScope : Scope<GameHandler, ArenaGameScope>()
//
//object ArenaLobbyScope : Scope<LobbyHandler, ArenaLobbyScope>()

object TeamScope : Scope<cz.creeperface.hytale.bedwars.api.arena.Team, TeamScope>() {

    override val parent = _root_ide_package_.cz.creeperface.hytale.bedwars.api.placeholder.ArenaScope

    fun getContext(context: cz.creeperface.hytale.bedwars.api.arena.Team): Context {
        return super.getContext(
            context,
            _root_ide_package_.cz.creeperface.hytale.bedwars.api.placeholder.ArenaScope.getContext(context.arena)
        )
    }
}