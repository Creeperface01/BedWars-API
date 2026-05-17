package cz.creeperface.hytale.bedwars.api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Stat {
    @SerialName("kills")
    KILLS,
    @SerialName("deaths")
    DEATHS,
    @SerialName("wins")
    WINS,
    @SerialName("losses")
    LOSSES,
    @SerialName("beds")
    BEDS,
    @SerialName("place")
    PLACE,
    @SerialName("break")
    BREAK,
    @SerialName("hits")
    HITS,
    @SerialName("games")
    GAMES;

    private val statName: String = name.lowercase()

    fun getName() = statName
}
