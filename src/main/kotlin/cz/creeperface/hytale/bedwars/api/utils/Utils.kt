@file:OptIn(ExperimentalContracts::class)

package cz.creeperface.hytale.bedwars.api.utils

import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import cz.creeperface.hytale.bedwars.api.arena.Arena
import cz.creeperface.hytale.bedwars.api.arena.State
import cz.creeperface.hytale.bedwars.api.arena.Team
import cz.creeperface.hytale.bedwars.api.placeholder.ArenaScope
import cz.creeperface.hytale.bedwars.api.placeholder.TeamScope
import cz.creeperface.hytale.placeholderapi.api.scope.Scope
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

typealias ArenaContext = Scope<Arena, ArenaScope>.Context
typealias TeamContext = Scope<Team, TeamScope>.Context

operator fun <T, E : Enum<E>> Array<T>.get(index: Enum<E>) = this[index.ordinal]

operator fun <T, E : Enum<E>> Array<T>.set(index: Enum<E>, value: T) {
    this[index.ordinal] = value
}

inline fun <reified T : Arena, R> Arena.handle(
    action: T.() -> R
): R? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this is T) {
        return action(this)
    }

    return null
}

inline fun <reified T : Arena, R> Arena.handle(
    state: State<T>,
    action: T.() -> R
): R? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this.state == state) {
        return this.handle(action)
    }

    return null
}

fun Player.ref(): PlayerRef? {
    val ref = this.reference ?: return null
    return ref.store.getComponent(ref, PlayerRef.getComponentType())
}
