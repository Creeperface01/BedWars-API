package com.creeperface.nukkit.bedwars.api.event

import cn.nukkit.event.HandlerList
import com.creeperface.nukkit.bedwars.api.BedWarsAPI
import com.creeperface.nukkit.bedwars.api.arena.Arena

class ArenaStartEvent(
    api: BedWarsAPI,
    arena: Arena
) : ArenaEvent(api, arena) {

    companion object {
        @JvmStatic
        private val handlers = HandlerList()

        @JvmStatic
        fun getHandlers(): HandlerList {
            return handlers
        }
    }
}