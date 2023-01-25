package com.zorbeytorunoglu.kLib.extensions

import com.zorbeytorunoglu.kLib.MCPlugin
import com.zorbeytorunoglu.kLib.task.MCDispatcher
import com.zorbeytorunoglu.kLib.task.Scopes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.bukkit.event.Listener
import java.io.IOException
import java.net.URL
import java.util.*


fun MCPlugin.registerEvents(vararg listeners: Listener) {
    listeners.forEach { server.pluginManager.registerEvents(it, this) }
}

fun MCPlugin.info(log: String) = this.logger.info(log)

fun MCPlugin.severe(log: String) = this.logger.severe(log)

fun MCPlugin.warning(log: String) = this.logger.warning(log)

suspend fun MCPlugin.getLatestVersionSpigot(resourceId: String): String? {

    return Scopes.ioScope.async {
        try {
            withContext(Dispatchers.IO) {
                URL("https://api.spigotmc.org/legacy/update.php?resource=$resourceId").openStream()
            }.use {
                Scanner(it).use { scanner -> if (scanner.hasNext()) scanner.next() else null }
            }
        } catch (exception: IOException) {
            null
        }
    }.await()

}

suspend fun MCPlugin.checkForUpdate(spigotResourceId: String, currentVersion: String): Boolean? {

    return Scopes.ioScope.async {
        try {
            withContext(Dispatchers.IO) {
                URL("https://api.spigotmc.org/legacy/update.php?resource=$spigotResourceId").openStream()
            }.use {
                Scanner(it).use { scanner -> if (scanner.hasNext()) scanner.next().isVersionGreaterThan(currentVersion) else null }
            }
        } catch (exception: IOException) {
            null
        }
    }.await()

}