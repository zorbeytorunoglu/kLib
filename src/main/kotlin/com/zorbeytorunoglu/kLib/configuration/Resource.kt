package com.zorbeytorunoglu.kLib.configuration

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException
import java.nio.file.Files

class Resource: YamlConfiguration {

    val file: File

    /**
     * Creates a file in plugin's data folder or copies if a file with the same name exists in resources.
     * @param plugin Plugin.
     * @param name Name of the file with its extension (example: .yml).
     */
    constructor(plugin: Plugin, name: String) {
        this.file = File(plugin.dataFolder, name)
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) {
            if (plugin.getResource(name) == null) file.createNewFile() else
                plugin.saveResource(name, true)
        }
    }

    /**
     * Creates a file in the specified folder or copies if a file with the same name exists in resources.
     * @param path Desired path of the file.
     * @param fileName Name of the file with its extension (example: .yml).
     */
    constructor(path: String, fileName: String) {

        this.file = File(path)
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) {
            try {
                javaClass.getResourceAsStream("/$fileName").use {
                    `in` -> `in`?.let { Files.copy(it, file.toPath()) }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Loads the file.
     * @return Resource object.
     */
    fun load(): Resource {
        try {
            super.load(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return this
    }

    /**
     * Saves the file.
     * @return Resource object.
     */
    fun save(): Resource {
        try {
            super.save(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return this
    }

}