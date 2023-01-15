package com.zorbeytorunoglu.kLib.extensions

import org.bukkit.World

fun World.removeEntities() = this.entities.forEach { it.remove() }

