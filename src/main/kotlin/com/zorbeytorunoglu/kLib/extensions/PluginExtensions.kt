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


/**
 * Register multiple events.
 * @param listeners vararg of listener classes.
 */
fun MCPlugin.registerEvents(vararg listeners: Listener) {
    listeners.forEach { server.pluginManager.registerEvents(it, this) }
}

/**
 * Logs an info log.
 * @param log string to be logged.
 */
fun MCPlugin.info(log: String) = this.logger.info(log)

/**
 * Logs a severe log.
 * @param log string to be logged.
 */
fun MCPlugin.severe(log: String) = this.logger.severe(log)

/**
 * Logs a warning log.
 * @param log string to be logged.
 */
fun MCPlugin.warning(log: String) = this.logger.warning(log)

/**
 * Gets the latest version of the resource from SpigotAPI async.
 * @param spigotResourceId ID of the resource.
 * @return Version as string if not null.
 */
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

/**
 * Gets the latest version of the resource from SpigotAPI.
 * @param spigotResourceId ID of the resource.
 * @return Version as string if not null.
 */
fun MCPlugin.getLatestVersion(spigotResourceId: String): String? =
    Scanner(URL("https://api.spigotmc.org/legacy/update.php?resource=$spigotResourceId").openStream())
    .use { scanner -> if (scanner.hasNext()) scanner.next() else null }

/**
 * Checks if the version of the resource in Spigot is greater than the given (current) one async.
 * @param spigotResourceId ID of the resource.
 * @param currentVersion Current version of the plugin.
 * @return True if there is a greater version, false if no, null if can't be reached.
 */
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

/**
 * Checks if the version of the resource in Spigot is greater than the given (current) one.
 * @param spigotResourceId ID of the resource.
 * @param currentVersion Current version of the plugin.
 * @return True if there is a greater version, false if no, null in case of an error.
 */
fun MCPlugin.checkForUpdate(spigotResourceId: String, currentVersion: String): Boolean? =
    Scanner(URL("https://api.spigotmc.org/legacy/update.php?resource=$spigotResourceId").openStream())
        .use { scanner -> if (scanner.hasNext()) scanner.next().isVersionGreaterThan(currentVersion) else null }


/**
 * Gets the latest GitHub release's tag async.
 * @param repoOwner GitHub name of the owner of the repository.
 * @param repoName Repository name.
 * @return Tag string.
 */
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

/**
 * Gets the latest GitHub release's tag.
 * @param repoOwner GitHub name of the owner of the repository.
 * @param repoName Repository name.
 * @return Tag string.
 */
fun MCPlugin.getLatestReleaseTag(repoOwner: String, repoName: String): String? =
    Gson().fromJson(BufferedReader(InputStreamReader(URL("https://api.github.com/repos/$repoOwner/$repoName/releases/latest")
            .openStream())), JsonObject::class.java)?.get("tag_name")?.asString