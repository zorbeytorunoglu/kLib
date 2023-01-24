package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.Bukkit
import org.bukkit.Chunk
import org.bukkit.World

fun World.removeEntities() = this.entities.forEach { it.remove() }

fun Chunk.toLegibleString(): String = this.world.name+","+this.x+","+this.z

fun Chunk.fromString(string: String): Chunk? {

    val varargs = string.split(",")

    return try {
        val world = Bukkit.getWorld(varargs[0])
        world.getChunkAt(varargs[1].toInt(),varargs[2].toInt())
    } catch (e: Exception) {
        null
    }

}