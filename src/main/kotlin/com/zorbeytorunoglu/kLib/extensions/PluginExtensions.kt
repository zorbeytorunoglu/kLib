package com.zorbeytorunoglu.kLib.extensions

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.zorbeytorunoglu.kLib.MCPlugin
import com.zorbeytorunoglu.kLib.task.Scopes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.bukkit.event.Listener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.*


fun MCPlugin.registerEvents(vararg listeners: Listener) {
    listeners.forEach { server.pluginManager.registerEvents(it, this) }
}

fun MCPlugin.info(log: String) = this.logger.info(log)

fun MCPlugin.severe(log: String) = this.logger.severe(log)

fun MCPlugin.warning(log: String) = this.logger.warning(log)

suspend fun MCPlugin.getLatestVersionAsync(spigotResourceId: String): String? {

    return Scopes.ioScope.async {
        try {
            withContext(Dispatchers.IO) {
                URL("https://api.spigotmc.org/legacy/update.php?resource=$spigotResourceId").openStream()
            }.use {
                Scanner(it).use { scanner -> if (scanner.hasNext()) scanner.next() else null }
            }
        } catch (exception: IOException) {
            null
        }
    }.await()

}

fun MCPlugin.getLatestVersion(spigotResourceId: String): String? =
    Scanner(URL("https://api.spigotmc.org/legacy/update.php?resource=$spigotResourceId").openStream())
    .use { scanner -> if (scanner.hasNext()) scanner.next() else null }

suspend fun MCPlugin.checkForUpdateAsync(spigotResourceId: String, currentVersion: String): Boolean? {

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

fun MCPlugin.checkForUpdate(spigotResourceId: String, currentVersion: String): Boolean? =
    Scanner(URL("https://api.spigotmc.org/legacy/update.php?resource=$spigotResourceId").openStream())
        .use { scanner -> if (scanner.hasNext()) scanner.next().isVersionGreaterThan(currentVersion) else null }

suspend fun MCPlugin.getLatestReleaseTagAsync(repoOwner: String, repoName: String): String? {

    return Scopes.ioScope.async {
        try {
            withContext(Dispatchers.IO) {
                URL("https://api.github.com/repos/$repoOwner/$repoName/releases/latest").openStream()
            }.use {
                Gson().fromJson(BufferedReader(InputStreamReader(it)), JsonObject::class.java)?.get("tag_name")?.asString
            }
        } catch (exception: IOException) {
            null
        }
    }.await()

}

fun MCPlugin.getLatestReleaseTag(repoOwner: String, repoName: String): String? =
    Gson().fromJson(BufferedReader(InputStreamReader(URL("https://api.github.com/repos/$repoOwner/$repoName/releases/latest")
            .openStream())), JsonObject::class.java)?.get("tag_name")?.asString