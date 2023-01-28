package com.zorbeytorunoglu.kLib.cuboid

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.*
import kotlin.math.abs

/**
 * Creates a Cuboid object symbolizes a cuboid region with two location points.
 * @param point1 First point location
 * @param point2 Second point location
 */
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

    /**
     * Gets the array list of blocks in this cuboid region.s
     */
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

    /**
     * Gets the center location of the cuboid region.
     */
    val center: Location get() {
        return Location(
            world,
            ((xMax - xMin) / 2 + xMin).toDouble(),
            ((yMax - yMin) / 2 + yMin).toDouble(),
            ((zMax - zMin) / 2 + zMin).toDouble()
        )
    }

    /**
     * Gets the distance between to points of the cuboid.
     */
    val distance: Double get() = point1.distance(point2)

    /**
     * Gets the squared distance of two points of the cuboid region.
     */
    val distanceSquared: Double get() = point1.distanceSquared(point2)

    /**
     * Gets the total block size of the cuboid region.
     */
    val totalBlockSize: Int get() = height * xWidth * zWidth

    /**
     * Gets the width of X.
     */
    val xWidth: Int get() = xMax - xMin +1

    /**
     * Gets the width of Z.
     */
    val zWidth: Int get() = zMax - zMin + 1

    /**
     * Gets a random location in the cuboid region.
     */
    val randomLocation: Location get() {
        val rand = Random()
        val x: Int = rand.nextInt(abs(xMax - xMin) + 1) + xMin
        val y: Int = rand.nextInt(abs(yMax - yMin) + 1) + yMin
        val z: Int = rand.nextInt(abs(zMax - zMin) + 1) + zMin
        return Location(world, x.toDouble(), y.toDouble(), z.toDouble())
    }

    /**
     * Gets the height of the cuboid.
     */
    val height: Int get() = yMax - yMin + 1

    /**
     * Checks if the given location is in the cuboid.
     * @param loc Location
     * @return Boolean
     */
    fun isIn(loc: Location): Boolean {
        return loc.world === world && loc.blockX >= xMin && loc.blockX <= xMax && loc.blockY >= yMin && loc.blockY <= yMax && loc
            .blockZ >= zMin && loc.blockZ <= zMax
    }

    /**
     * Checks if the player is in the cuboid.
     * @param player Player
     * @return Boolean
     */
    fun isIn(player: Player): Boolean {
        return isIn(player.location)
    }

    /**
     * Checks if given location is in the cuboid with marge option.
     * @param loc Location
     * @param marge Marge boolean
     * @return Boolean
     */
    fun isInWithMarge(loc: Location, marge: Double): Boolean {
        return loc.world === world && loc.x >= xMinCentered - marge && loc.x <= xMaxCentered + marge && loc.y >= yMinCentered - marge && loc
            .y <= yMaxCentered + marge && loc.z >= zMinCentered - marge && loc.z <= zMaxCentered + marge
    }

}