package com.creeperface.nukkit.bedwars.api.utils

import com.creeperface.nukkit.bedwars.api.arena.Arena
import com.creeperface.nukkit.bedwars.api.arena.State
import com.creeperface.nukkit.bedwars.api.arena.Team
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapConfiguration
import com.creeperface.nukkit.bedwars.api.arena.configuration.MapFilter
import com.creeperface.nukkit.bedwars.api.arena.configuration.MutableConfiguration
import com.creeperface.nukkit.bedwars.api.placeholder.ArenaScope
import com.creeperface.nukkit.bedwars.api.placeholder.TeamScope
import com.creeperface.nukkit.placeholderapi.api.scope.Scope
import java.time.Instant
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

typealias ArenaContext = Scope<Arena, ArenaScope>.Context
typealias TeamContext = Scope<Team, TeamScope>.Context

operator fun <T, E : Enum<E>> Array<T>.get(index: Enum<E>) = this[index.ordinal]

operator fun <T, E : Enum<E>> Array<T>.set(index: Enum<E>, value: T) {
    this[index.ordinal] = value
}

operator fun <T> Boolean.invoke(action: () -> T): T? = if (this) action() else null

inline fun <reified T, reified V> MutableConfiguration.watching(defaultValue: V? = null) =
    watch<T, V>(this, defaultValue)

inline fun <reified T, reified V> watch(conf: MutableConfiguration, defaultValue: V? = null) =
    object : ReadWriteProperty<T, V> {

        var value: V

        init {
            if (null !is V && defaultValue == null) {
                error("Cannot set default value to null of nonnull property")
            }

            value = defaultValue as V
        }

        override fun getValue(thisRef: T, property: KProperty<*>): V = value

        override fun setValue(thisRef: T, property: KProperty<*>, value: V) {
            conf.lastModification = Instant.now()
        }
    }

fun MapFilter.applyFilter(configurations: Collection<MapConfiguration>): Collection<MapConfiguration> {
    if (enable) {
        return configurations.filter {
            when {
                this.teamCount.isNotEmpty() && it.teams.size !in this.teamCount -> false
                this.include.isNotEmpty() && !this.include.contains(it.name) -> false
                this.exclude.isNotEmpty() && this.exclude.contains(it.name) -> false
                else -> true
            }
        }
    }

    return configurations
}

inline fun <reified T : Arena, R> Arena.handle(action: T.() -> R): R? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    require(!closed) {
        "Accessing unused Arena object"
    }

    val handler = getHandler()

    if (handler is T) {
        return action(handler)
    }

    return null
}

inline fun <reified T : Arena, R> Arena.handle(state: State<T>, action: T.() -> R): R? {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }

    if (this.state == state) {
        return this.handle(action)
    }

    return null
}