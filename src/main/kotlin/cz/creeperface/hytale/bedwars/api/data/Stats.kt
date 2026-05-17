package cz.creeperface.hytale.bedwars.api.data

import cz.creeperface.hytale.bedwars.api.utils.get
import cz.creeperface.hytale.bedwars.api.utils.set

class Stats(private val statsOriginal: Array<Int>) {

    private val stats = Array(_root_ide_package_.cz.creeperface.hytale.bedwars.api.data.Stat.entries.size) { 0 }

    fun getDelta(stat: cz.creeperface.hytale.bedwars.api.data.Stat): Int {
        return stats[stat]
    }

    fun add(stat: cz.creeperface.hytale.bedwars.api.data.Stat, value: Int = 1) {
        stats[stat] = stats[stat] + value
    }

    operator fun get(stat: cz.creeperface.hytale.bedwars.api.data.Stat): Int {
        return stats[stat] + statsOriginal[stat]
    }

    companion object {

        fun initial() = Stats(Array(_root_ide_package_.cz.creeperface.hytale.bedwars.api.data.Stat.entries.size) { 0 })
    }
}
