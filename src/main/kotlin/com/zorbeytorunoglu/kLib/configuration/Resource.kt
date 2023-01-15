package com.zorbeytorunoglu.kLib.configuration

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException
import java.nio.file.Files

class Resource: YamlConfiguration {

    val file: File

    constructor(plugin: Plugin, name: String) {
        this.file = File(plugin.dataFolder, name)
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        if (!file.exists()) {
            if (plugin.getResource(name) == null) file.createNewFile() else
                plugin.saveResource(name, true)
        }
    }

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

    fun load(): Resource {
        try {
            super.load(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return this
    }

    fun save(): Resource {
        try {
            super.save(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return this
    }

}