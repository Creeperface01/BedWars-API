package com.creeperface.nukkit.bedwars.api.data

enum class Stat {
    KILLS,
    DEATHS,
    WINS,
    LOSSES,
    BEDS,
    PLACE,
    BREAK,
    HITS,
    GAMES;

    private val statName: String = name.lowercase()

    fun getName() = statName
}
