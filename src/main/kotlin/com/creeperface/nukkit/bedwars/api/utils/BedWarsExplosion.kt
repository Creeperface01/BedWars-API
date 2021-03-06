package com.creeperface.nukkit.bedwars.api.utils

import cn.nukkit.Player
import cn.nukkit.entity.Entity
import cn.nukkit.event.entity.EntityDamageByEntityEvent
import cn.nukkit.event.entity.EntityDamageEvent
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause
import cn.nukkit.level.Level
import cn.nukkit.level.Position
import cn.nukkit.level.Sound
import cn.nukkit.level.particle.HugeExplodeParticle
import cn.nukkit.math.NukkitMath
import cn.nukkit.math.SimpleAxisAlignedBB
import cn.nukkit.math.Vector3
import com.creeperface.nukkit.bedwars.api.arena.handler.GameHandler
import kotlin.math.max

/**
 * Created by CreeperFace on 14. 12. 2016.
 */
class BedWarsExplosion(private val source: Position, size: Double, private val what: Entity?) {

    private val level: Level = source.getLevel()
    private val size: Double = max(size, 0.0)

    fun explode(arena: GameHandler, team: Int): Boolean {
        val source = Vector3(this.source.x, this.source.y, this.source.z).floor()

        val explosionSize = this.size * 2.0
        val minX = NukkitMath.floorDouble(this.source.x - explosionSize - 1.0).toDouble()
        val maxX = NukkitMath.ceilDouble(this.source.x + explosionSize + 1.0).toDouble()
        val minY = NukkitMath.floorDouble(this.source.y - explosionSize - 1.0).toDouble()
        val maxY = NukkitMath.ceilDouble(this.source.y + explosionSize + 1.0).toDouble()
        val minZ = NukkitMath.floorDouble(this.source.z - explosionSize - 1.0).toDouble()
        val maxZ = NukkitMath.ceilDouble(this.source.z + explosionSize + 1.0).toDouble()

        val explosionBB = SimpleAxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ)

        val list = this.level.getNearbyEntities(explosionBB, this.what)
        for (entity in list) {

            if (entity is Player) {
                val playerTeam = arena.getPlayerData(entity)?.team ?: continue

                if (playerTeam.id == team)
                    continue
            }

            val distance = entity.distance(this.source) / explosionSize

            if (distance <= 1) {
                val motion = entity.subtract(this.source).normalize()
                val exposure = 1
                val impact = (1 - distance) * exposure
                val damage = ((impact * impact + impact) / 2 * 8.0 * explosionSize + 1).toInt()

                if (this.what != null) {
                    val ev =
                        EntityDamageByEntityEvent(this.what, entity, DamageCause.ENTITY_EXPLOSION, damage.toFloat())
                    entity.attack(ev)
                } else {
                    val ev = EntityDamageEvent(entity, DamageCause.BLOCK_EXPLOSION, damage.toFloat())
                    entity.attack(ev)
                }

                entity.motion = motion.multiply(impact)
            }
        }

        this.level.addParticle(HugeExplodeParticle(source))
        this.level.addSound(source, Sound.RANDOM_EXPLODE)
        return true
    }
}
