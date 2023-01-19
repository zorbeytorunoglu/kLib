package com.zorbeytorunoglu.kLib.configuration

import com.zorbeytorunoglu.kLib.MCPlugin
import org.bukkit.configuration.file.YamlConfiguration

fun MCPlugin.createYamlResource(fileName: String): Resource = Resource(this, fileName)

fun MCPlugin.createLoadYamlResource(fileName: String): YamlConfiguration =
    YamlConfiguration.loadConfiguration(Resource(this, fileName).file)

fun MCPlugin.createFileWithPath(path: String, fileName: String): Resource = Resource(path, fileName)