package com.zorbeytorunoglu.kLib.extensions

import com.zorbeytorunoglu.kLib.MCPlugin
import org.bukkit.event.Listener

fun MCPlugin.registerEvents(vararg listeners: Listener) {
    listeners.forEach { server.pluginManager.registerEvents(it, this) }
}

fun MCPlugin.info(log: String) = this.logger.info(log)

fun MCPlugin.severe(log: String) = this.logger.severe(log)

fun MCPlugin.warning(log: String) = this.logger.warning(log)