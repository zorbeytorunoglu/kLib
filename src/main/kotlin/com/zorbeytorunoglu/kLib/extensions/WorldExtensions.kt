package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.Bukkit
import org.bukkit.Chunk
import org.bukkit.Sound
import org.bukkit.World

/**
 * Removes all the entities in the world's list.
 */
fun World.removeEntities() = this.entities.forEach { it.remove() }

/**
 * Parses the chunk to string.
 * @return String
 */
fun Chunk.toLegibleString(): String = this.world.name+","+this.x+","+this.z

/**
 * Gets a chunk from string.
 * @param string String
 * @return Chunk
 */
fun Chunk.fromString(string: String): Chunk? {

    val varargs = string.split(",")

    return try {
        val world = Bukkit.getWorld(varargs[0])
        world.getChunkAt(varargs[1].toInt(),varargs[2].toInt())
    } catch (e: Exception) {
        null
    }

}

/**
 * Plays sound for player in the world.
 * @param sound Sound
 * @param volume Volume Float
 * @param pitch Pitch Float
 */
fun World.playSound(sound: Sound, volume: Float, pitch: Float) {
    this.players.forEach {
        it.playSound(sound, volume, pitch)
    }
}