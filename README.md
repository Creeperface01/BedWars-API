# BedWars API

Public API for the BedWars Hytale plugin. Third-party plugins depend on this module to integrate with BedWars -- reading
game state, reacting to events, extending configuration, and adding custom UI to the configurator.

**Source & releases:** https://github.com/Creeperface01/BedWars-API

## Setup

Add the BedWars API JAR as a `compileOnly` dependency:

```kotlin
dependencies {
    compileOnly(files("libs/BedWarsAPI.jar"))
}
```

Access the API singleton after BedWars has loaded:

```kotlin
val api = BedWarsAPI.instance
```

## API Overview

### BedWarsAPI

Central entry point. Provides access to all subsystems:

```kotlin
val api = BedWarsAPI.instance

api.getArenas()                // All active arenas
api.getArena("lobby1")         // Arena by name
api.getPlayerArena(playerRef)  // Arena a player is in
api.joinRandomArena(playerRef) // Join best available arena

api.economyProvider            // Economy integration
api.dataProvider               // Player stats persistence
api.configExtensions           // Config extension registry
```

### Arenas

```kotlin
val arena: Arena = api.getArena("lobby1") ?: return

arena.config                   // ArenaConfiguration (timing, players, shop, etc.)
arena.players                  // Map<String, PlayerRef> — everyone in the arena
arena.state                    // Current state: Waiting / Voting / TeamSelect / Game
arena.context                  // PlaceholderAPI scope context
arena.closed                   // True when the arena cannot be joined
arena.inArena(playerRef)       // Whether a player is in this arena
arena.joinToArena(playerRef)   // Add a player (returns Boolean)
arena.leaveArena(playerRef)    // Remove a player
arena.broadcastMessage(msg)    // Message everyone (players + spectators)
```

The live game is exposed through the `Game` state's `GameHandler` — `null` unless a match is running:

```kotlin
import cz.creeperface.hytale.bedwars.api.arena.State

val game = (arena.state as? State.Game)?.handler ?: return

game.mapConfig                 // MapConfiguration being played
game.teams                     // List<Team>
game.aliveTeams                // Teams still in the running
game.winner                    // Team? — set once the game ends
game.ending                    // True while the end screen is showing
game.getPlayerData(playerRef)  // PlayerData? for a player in the game
game.getPlayerTeam(playerRef)  // Team? for a player
game.getTeam(id)               // Team? by id
game.isSpectator(playerRef)    // Whether the player is spectating
```

### Teams

```kotlin
val team: Team = game.getPlayerTeam(playerRef) ?: return

team.id                        // Stable team index
team.config                    // TeamConfiguration (name, color, spawn, bed)
team.playerCount               // Members currently on the team
team.hasBed()                  // Whether the bed is intact
team.isAlive()                 // Whether the team has living players
team.getTeamPlayers()          // Map<String, PlayerData> of members
team.destroyBed()              // Programmatically break the bed
team.messagePlayers(message)   // Message everyone on the team
```

### Player Data

`PlayerData` is per-game — fetch it from the `GameHandler`:

```kotlin
import cz.creeperface.hytale.bedwars.api.data.Stat

val data: PlayerData = game.getPlayerData(playerRef) ?: return

data.playerRef                 // The PlayerRef
data.team                      // Their Team
data.arena                     // The Arena
data.getStat(Stat.KILLS)       // Session delta for a stat (this game only)
data.canRespawn()              // Whether they can still respawn (team has a bed)
```

Spectator status lives on the handler: `game.isSpectator(playerRef)`.

## Events

All events extend `ArenaEvent` and work with Hytale's event bus. Events with `isCancelled` are cancellable.

| Event                       | Description                                     |
|-----------------------------|-------------------------------------------------|
| `ArenaStartEvent`           | Game starts                                     |
| `ArenaStopEvent`            | Arena stops (with cause)                        |
| `ArenaStateChangeEvent`     | State transition (Voting -> TeamSelect -> Game) |
| `ArenaPlayerJoinEvent`      | Player joins arena                              |
| `ArenaPlayerLeaveEvent`     | Player leaves arena                             |
| `ArenaKillEvent`            | Participant kills another (player or bot)       |
| `ArenaPlayerEliminateEvent` | Player eliminated (no bed + died)               |
| `ArenaPlayerRespawnEvent`   | Player respawns                                 |
| `ArenaTeamEliminateEvent`   | Entire team eliminated                          |
| `ArenaBedDestroyEvent`      | Bed destroyed (cancellable)                     |
| `ArenaShopPurchaseEvent`    | Shop purchase (cancellable)                     |
| `ArenaResourceDropEvent`    | Resource generator drops item (cancellable)     |

Example:

```kotlin
eventRegistry.registerGlobal(ArenaKillEvent::class.java) { event ->
    val killer = event.killer
  val victim = event.victim
    // Award bonus currency, announce, etc.
}
```

## Economy Provider

Implement a custom economy backend. All operations return `CompletableFuture`, and `currency` defaults to
`defaultCurrency`. Only the methods below are abstract — `subtractMoney`, the `PlayerRef` overloads, and the
default-currency variants are derived from them.

```kotlin
class MyEconomyProvider : EconomyProvider {
  override val defaultCurrency: EconomyProvider.Currency = MyCurrency

  // Credit a player by UUID
  override fun addMoney(
    player: UUID, amount: Double, currency: EconomyProvider.Currency
  ): CompletableFuture<Void> {
    ...
  }

  // Read a balance by username
  override fun getMoney(
    player: String, currency: EconomyProvider.Currency
  ): CompletableFuture<Double> {
    ...
  }

  override fun transferMoney(
    from: String, to: String, amount: Double, currency: EconomyProvider.Currency
  ): CompletableFuture<Boolean> {
    ...
  }

  override fun getCurrency(name: String): EconomyProvider.Currency? {
    ...
  }
}
```

Register it from your plugin's `setup()` (before BedWars resolves providers), then set `economy_provider` in
`economy.json` to the name you registered:

```kotlin
BedWarsAPI.instance.registerEconomyProvider("mybackend") { MyEconomyProvider() }
```

## Data Provider

Implement custom player-stats storage (and, optionally, arena/map/shop config storage). Every method returns a
`CompletableFuture` — none are `suspend`:

```kotlin
class MyDataProvider : DataProvider {
  override fun init() {
    ...
  }    // optional — open connections
  override fun deinit() {
    ...
  }  // optional — close connections

  // ---- Player stats (identifier = player UUID string) ----
  override fun register(name: String, identifier: String): CompletableFuture<Void> {
    ...
  }
  override fun unregister(identifier: String): CompletableFuture<Void> {
    ...
  }
  override fun getData(identifier: String): CompletableFuture<Stats?> {
    ...
  }
  override fun getDataByName(name: String): CompletableFuture<Stats?> {
    ...
  }
  override fun saveData(identifier: String, data: Stats): CompletableFuture<Void> {
    ...
  }

  // ---- Config storage (used when config.json `store_configs` = true) ----
  override fun loadArenaConfigs(): CompletableFuture<Map<String, ArenaConfiguration>> {
    ...
  }
  override fun saveArenaConfig(name: String, config: ArenaConfiguration): CompletableFuture<Void> {
    ...
  }
  override fun deleteArenaConfig(name: String): CompletableFuture<Void> {
    ...
  }
  override fun loadMapConfigs(): CompletableFuture<Map<String, MapConfiguration>> {
    ...
  }
  override fun saveMapConfig(name: String, config: MapConfiguration): CompletableFuture<Void> {
    ...
  }
  override fun deleteMapConfig(name: String): CompletableFuture<Void> {
    ...
  }
  override fun loadShopConfigs(): CompletableFuture<Map<String, ShopConfig>> {
    ...
  }
  override fun saveShopConfig(name: String, config: ShopConfig): CompletableFuture<Void> {
    ...
  }
  override fun deleteShopConfig(name: String): CompletableFuture<Void> {
    ...
  }
}
```

Register it from your plugin's `setup()`, then set `data_provider` in `config.json` to the name you registered:

```kotlin
BedWarsAPI.instance.registerDataProvider("mybackend") { MyDataProvider() }
```

A `Stats` object exposes `stats[Stat.KILLS]` (running total), `getDelta(stat)` (this session), and `add(stat, value)`.

## Config Extensions

Add custom typed data to arena, map, or shop configurations. The data is stored in the config's `extensions` JSON field
and persisted alongside built-in fields -- in both files and the database.

### Defining a Config Section

```kotlin
@Serializable
data class KitsConfig(
    val enabled: Boolean = false,
    val maxLevel: Int = 3,
    val kits: List<Kit> = emptyList()
)

@Serializable
data class Kit(val name: String = "", val items: List<String> = emptyList())

// Create the section definition
val KITS_SECTION = ConfigSection(
    id = "myplugin:kits",          // Unique namespaced ID
    targetConfig = "map",           // Attaches to map configs ("arena", "map", or "shop")
    serializer = KitsConfig.serializer(),
    default = KitsConfig()
)
```

### Registering

```kotlin
// In your plugin's setup()
BedWarsAPI.instance.configExtensions.register(KITS_SECTION)
```

### Reading Extension Data

```kotlin
// In game logic -- read from the map config of a running game
val mapConfig: MapConfiguration = (arena.state as? State.Game)?.handler?.mapConfig ?: return
val kits: KitsConfig = mapConfig.extensions.getSection(KITS_SECTION)

if (kits.enabled) {
    // Use typed data...
}
```

### Writing Extension Data

```kotlin
// Update extension data on a config
val updated = mapConfig.extensions.withSection(KITS_SECTION, kits.copy(maxLevel = 5))
```

### How It Is Stored

Extension data is stored inside the config JSON under the `extensions` key:

```json
{
  "name": "Castle",
  "teams": [...],
  "extensions": {
    "myplugin:kits": {
      "enabled": true,
      "maxLevel": 3,
      "kits": [...]
    }
  }
}
```

Unknown extensions are preserved through load/save cycles.
Missing extensions return the default value defined in the `ConfigSection`.

## UI Extension Points

Inject custom UI elements into the BedWars configurator pages. Extension points are predefined slots where plugins can
add buttons, form fields, sections, or navigate to entirely custom pages.

### Available Extension Points

| Extension Point        | Location                                                | Context            |
|------------------------|---------------------------------------------------------|--------------------|
| `MAIN_PAGE_BUTTONS`    | Main configurator page, after Arenas/Maps/Shops buttons | `MainPageContext`  |
| `ARENA_SETUP_SECTIONS` | Arena setup page, before Save/Discard                   | `ArenaEditContext` |
| `MAP_SETUP_BUTTONS`    | Map setup page, alongside Teams/Resources buttons       | `MapEditContext`   |
| `MAP_SETUP_SECTIONS`   | Map setup page, before Save/Discard                     | `MapEditContext`   |
| `SHOP_SETUP_SECTIONS`  | Shop setup page, before Save/Discard                    | `ShopEditContext`  |

### Adding a Button to the Map Setup Page

```kotlin
UiExtensionPoints.MAP_SETUP_BUTTONS.register { ctx ->
    val builder = ctx.builder as ChildNodeBuilder

    builder.group {
        layoutMode = LayoutMode.Center
        anchor = UiAnchor(bottom = 8, height = 48)

        defaultTextButton {
            text = "Manage Kits".translated()
            anchor = anchor.getOrDefault().copy(height = 44)
            onActivate {
                // Navigate to your fully custom page
                MyKitsPage.show(ctx.playerRef, ctx.extensions)
            }
        }
    }
}
```

### Adding Form Fields to the Arena Setup Page

```kotlin
UiExtensionPoints.ARENA_SETUP_SECTIONS.register { ctx ->
    val builder = ctx.builder as ChildNodeBuilder
    val myConfig = ctx.extensions.getSection(MY_ARENA_SECTION)

    builder.divider()
    builder.subtitle { text = "My Plugin Settings".translated() }

    builder.group {
        layoutMode = LayoutMode.Left
        anchor = UiAnchor(bottom = 10, height = 32)

        label { text = "Enable Feature".translated(); anchor = UiAnchor(width = 160) }

        defaultCheckBox {
            value = myConfig.enabled
        }.onValueChange { v: Boolean? ->
            if (v != null) {
                ctx.updateExtensions { extensions ->
                    extensions.withSection(MY_ARENA_SECTION, myConfig.copy(enabled = v))
                }
            }
        }
    }
}
```

### Custom Pages

For complex configuration, register your own page with UiManager and navigate to it from an extension point button:

```kotlin
// Register page in your plugin's setup()
UiManager.registerPage("mypluginKitsSetup", KitsPageData()) { playerRef, data ->
    pageOverlay {
        decoratedContainer {
            title { defaultTitle { text = "Kits Configuration".toMessage() } }
            content {
                // Full UiManager DSL -- lists, tabs, dropdowns, position pickers, etc.
            }
        }
    }
}
```

### Context Properties

All edit contexts provide:

- `builder: Any` -- The UiManager `ChildNodeBuilder`. Cast to use DSL.
- `playerRef: PlayerRef` -- The player viewing the page.
- `extensions: JsonObject` -- Current extension data from the config draft.
- `updateExtensions: ((JsonObject) -> JsonObject) -> Unit` -- Callback to update extension data. The draft is
  auto-persisted after the callback.

`MainPageContext` provides only `builder` and `playerRef` (no config being edited).

## Stats

Player statistics tracked by BedWars:

| Stat     | Description    |
|----------|----------------|
| `KILLS`  | Players killed |
| `DEATHS` | Times died     |
| `WINS`   | Games won      |
| `LOSSES` | Games lost     |
| `BEDS`   | Beds destroyed |
| `PLACE`  | Blocks placed  |
| `BREAK`  | Blocks broken  |
| `HITS`   | Hits dealt     |
| `GAMES`  | Games played   |

Custom stats can be registered via `CustomStat.register(CustomStat(id, displayName))`.

## PlaceholderAPI Integration

BedWars provides placeholders through the PlaceholderAPI system. Scopes:

- `ArenaScope` -- Arena-level placeholders (player count, state, map name)
- `TeamScope` -- Team-level placeholders (team name, color, bed status)

Placeholders are available in chat formats, scoreboards, and HUD elements.
