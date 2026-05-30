package cz.creeperface.hytale.bedwars.api.data

import cz.creeperface.hytale.bedwars.api.utils.get
import cz.creeperface.hytale.bedwars.api.utils.set

class Stats(private val statsOriginal: Array<Int>) {

    private val stats = Array(Stat.entries.size) { 0 }

    fun getDelta(stat: Stat): Int {
        return stats[stat]
    }

    fun add(stat: Stat, value: Int = 1) {
        stats[stat] = stats[stat] + value
    }

    operator fun get(stat: Stat): Int {
        return stats[stat] + statsOriginal[stat]
    }

    companion object {

        fun initial() = Stats(Array(Stat.entries.size) { 0 })
    }
}
