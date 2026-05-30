package cz.creeperface.hytale.bedwars.api.arena.configuration

import com.hypixel.hytale.protocol.Vector3d
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.json.JsonObject

/**
 * Per-arena configuration. Serializable — loaded directly from JSON files.
 */
@Serializable
data class ArenaConfiguration(
    val name: String = "",
    @SerialName("time_limit") val timeLimit: Int = 3600,
    @SerialName("start_time") val startTime: Int = 60,
    @SerialName("ending_time") val endingTime: Int = 60,
    @SerialName("start_players") val startPlayers: Int = 8,
    @SerialName("max_players") val maxPlayers: Int = 16,
    @SerialName("team_players") val teamPlayers: Int = 4,
    @SerialName("fast_start") val fastStart: Boolean = true,
    @SerialName("fast_start_time") val fastStartTime: Int = 10,
    @SerialName("fast_start_players") val fastStartPlayers: Int = 14,
    @SerialName("respawn_delay") val respawnDelay: Int = 5,
    @SerialName("lobby_position") val lobbyPosition: Vec3 = Vec3(),
    @SerialName("lobby_world") val lobbyWorld: String = "",
    val shop: String = "",
    val voting: VotingConfig = VotingConfig(),
    @SerialName("map_filter") val mapFilter: MapFilterConfig = MapFilterConfig(),
    @SerialName("lobby_items") val lobbyItems: LobbyItemsConfiguration = LobbyItemsConfiguration(),
    val extensions: JsonObject = JsonObject(emptyMap())
)

/**
 * Per-map configuration. Serializable — loaded directly from JSON files.
 */
@Serializable
data class MapConfiguration(
    val name: String = "",
    val teams: List<TeamConfiguration> = emptyList(),
    val resources: List<ResourceConfiguration> = emptyList(),
    val extensions: JsonObject = JsonObject(emptyMap())
)

@Serializable
data class TeamConfiguration(
    val name: String = "",
    val color: String = "#ff0000",
    val spawn: Vec3 = Vec3(),
    val villager: Vec3 = Vec3(),
    val bed: Vec3 = Vec3()
)

@Serializable
data class ResourceConfiguration(
    val name: String = "",
    @SerialName("item_id") val itemId: String = "",
    @SerialName("drop_frequency") val dropFrequency: Int = 30,
    val positions: List<Vec3> = emptyList()
)

@Serializable
data class VotingConfig(
    val enable: Boolean = true,
    @SerialName("max_options") val maxOptions: Int = 3,
    val countdown: Int = 20
)

@Serializable
data class MapFilterConfig(
    val enable: Boolean = false,
    @SerialName("team_count") val teamCount: Set<Int> = emptySet(),
    val include: List<String> = emptyList(),
    val exclude: List<String> = emptyList()
)

@Serializable
data class LobbyItemsConfiguration(
    @SerialName("vote_item") val voteItem: String = "Deco_Map",
    @SerialName("team_select_item") val teamSelectItem: String = "Cloth_Roof_Green_Flap",
    @SerialName("leave_item") val leaveItem: String = "Plant_Coral_Block_Red",
    @SerialName("vote_slot") val voteSlot: Int = 0,
    @SerialName("team_select_slot") val teamSelectSlot: Int = 1,
    @SerialName("leave_slot") val leaveSlot: Int = 8
)

@Serializable
data class Vec3(
    val x: Double = 0.0,
    val y: Double = 64.0,
    val z: Double = 0.0
) {
    @Transient
    val vector3d: Vector3d
        get() = Vector3d(x, y, z)
}
