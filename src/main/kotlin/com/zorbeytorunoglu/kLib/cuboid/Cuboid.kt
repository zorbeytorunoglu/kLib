package com.zorbeytorunoglu.kLib.cuboid

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.*
import kotlin.math.abs


open class Cuboid(val point1: Location, val point2: Location) {

    private val xMin = point1.blockX.coerceAtMost(point2.blockX)
    private val xMax = point1.blockX.coerceAtLeast(point2.blockX)
    private val yMin = point1.blockY.coerceAtMost(point2.blockY)
    private val yMax = point1.blockY.coerceAtLeast(point2.blockY)
    private val zMin = point1.blockZ.coerceAtMost(point2.blockZ)
    private val zMax = point1.blockZ.coerceAtLeast(point2.blockZ)
    private val xMinCentered = xMin + 0.5
    private val xMaxCentered = xMax + 0.5
    private val yMinCentered = yMin + 0.5
    private val yMaxCentered = yMax + 0.5
    private val zMinCentered = zMin + 0.5
    private val zMaxCentered = zMax + 0.5
    private val world: World = point1.world

    val blockList: ArrayList<Block> get() {

        val blockList: ArrayList<Block> = ArrayList(totalBlockSize)

        for (x in xMin..xMax) {
            for (y in yMin..yMax) {
                for (z in zMin..zMax) {
                    val b = world.getBlockAt(x, y, z)
                    blockList.add(b)
                }
            }
        }

        return blockList

    }

    val center: Location get() {
        return Location(
            world,
            ((xMax - xMin) / 2 + xMin).toDouble(),
            ((yMax - yMin) / 2 + yMin).toDouble(),
            ((zMax - zMin) / 2 + zMin).toDouble()
        )
    }

    val distance: Double get() = point1.distance(point2)

    val distanceSquared: Double get() = point1.distanceSquared(point2)

    val totalBlockSize: Int get() = height * xWidth * zWidth

    val xWidth: Int get() = xMax - xMin +1

    val zWidth: Int get() = zMax - zMin + 1

    val randomLocation: Location get() {
        val rand = Random()
        val x: Int = rand.nextInt(abs(xMax - xMin) + 1) + xMin
        val y: Int = rand.nextInt(abs(yMax - yMin) + 1) + yMin
        val z: Int = rand.nextInt(abs(zMax - zMin) + 1) + zMin
        return Location(world, x.toDouble(), y.toDouble(), z.toDouble())
    }

    val height: Int get() = yMax - yMin + 1

    fun isIn(loc: Location): Boolean {
        return loc.world === world && loc.blockX >= xMin && loc.blockX <= xMax && loc.blockY >= yMin && loc.blockY <= yMax && loc
            .blockZ >= zMin && loc.blockZ <= zMax
    }

    fun isIn(player: Player): Boolean {
        return isIn(player.location)
    }

    fun isInWithMarge(loc: Location, marge: Double): Boolean {
        return loc.world === world && loc.x >= xMinCentered - marge && loc.x <= xMaxCentered + marge && loc.y >= yMinCentered - marge && loc
            .y <= yMaxCentered + marge && loc.z >= zMinCentered - marge && loc.z <= zMaxCentered + marge
    }

}