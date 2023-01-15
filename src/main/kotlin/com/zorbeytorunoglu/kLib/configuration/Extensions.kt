package com.zorbeytorunoglu.kLib.configuration

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.createYamlResource(fileName: String): Resource = Resource(this, fileName)

fun JavaPlugin.createLoadYamlResource(fileName: String): YamlConfiguration =
    YamlConfiguration.loadConfiguration(Resource(this, fileName).file)

fun JavaPlugin.createFileWithPath(path: String, fileName: String): Resource = Resource(path, fileName)