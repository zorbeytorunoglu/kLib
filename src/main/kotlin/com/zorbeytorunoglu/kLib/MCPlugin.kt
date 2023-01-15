package com.zorbeytorunoglu.kLib

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin

open class MCPlugin: JavaPlugin() {

    //TODO: Doesn't have any functionality right now. Reserved for future use.

    override fun onEnable() = runBlocking(SupervisorJob()) {
        super.onEnable()
    }

}