package com.zorbeytorunoglu.kLib.task

import com.zorbeytorunoglu.kLib.MCPlugin
import kotlinx.coroutines.*
import org.bukkit.scheduler.BukkitTask

/**
 * Suspends the function in ASYNC.
 * @param function Unit
 */
suspend fun MCPlugin.suspendFunctionAsync(function: () -> Unit) {
    Scopes.supervisorScope.launch(MCDispatcher(this, async = true)) {
        function()
    }.join()
}

suspend fun MCPlugin.suspendFunctionSync(function: () -> Unit) {
    Scopes.supervisorScope.launch(MCDispatcher(this, async = false)) {
        function()
    }.join()
}

suspend fun MCPlugin.suspendFunction(function: () -> Unit, async: Boolean) {
    Scopes.supervisorScope.launch(MCDispatcher(this, async = async)) {
        function()
    }.join()
}

suspend fun <T> MCPlugin.suspendFunctionSyncWithResult(function: () -> T): T {

    return Scopes.supervisorScope.async(MCDispatcher(this,async = false)) {

        function()

    }.await()

}

suspend fun <T> MCPlugin.suspendFunctionAsyncWithResult(function: () -> T): T {

    return Scopes.supervisorScope.async(MCDispatcher(this,async = true)) {

        function()

    }.await()

}

suspend fun <T> MCPlugin.suspendFunctionWithResult(async: Boolean, function: () -> T): T {

    return Scopes.supervisorScope.async(MCDispatcher(this,async)) {

        function()

    }.await()

}

fun MCPlugin.delay(function: () -> Unit, seconds: Int): BukkitTask {

    return this.server.scheduler.runTaskLater(this, {
        function()
    },seconds*20L)

}

fun MCPlugin.delay(function: () -> Unit, millis: Long): BukkitTask {

    return this.server.scheduler.runTaskLater(this, {
        function()
    }, millis)

}

fun MCPlugin.delayAsync(function: () -> Unit, seconds: Int): BukkitTask {

    return this.server.scheduler.runTaskLaterAsynchronously(this, {
        function()
    },seconds*20L)

}

fun MCPlugin.delayAsync(function: () -> Unit, millis: Long): BukkitTask {

    return this.server.scheduler.runTaskLaterAsynchronously(this, {
        function()
    }, millis)

}

fun MCPlugin.repeat(function: () -> Unit, seconds: Int): BukkitTask {

    return this.server.scheduler.runTaskTimer(this, {
        function()
    }, 0L, seconds * 20L)

}

fun MCPlugin.repeat(function: () -> Unit, millis: Long): BukkitTask {

    return this.server.scheduler.runTaskTimer(this, {
        function()
    }, 0L, millis)

}

fun MCPlugin.repeat(function: () -> Unit, delay: Int, period: Int): BukkitTask {

    return this.server.scheduler.runTaskTimer(this, {
        function()
    }, delay*20L, period * 20L)

}

fun MCPlugin.repeat(function: () -> Unit, delay: Long, period: Long): BukkitTask {

    return this.server.scheduler.runTaskTimer(this, {
        function()
    }, delay, period)

}

fun MCPlugin.repeatAsync(function: () -> Unit, seconds: Int): BukkitTask {

    return this.server.scheduler.runTaskTimerAsynchronously(this, {
        function()
    }, 0L, seconds * 20L)

}

fun MCPlugin.repeatAsync(function: () -> Unit, millis: Long): BukkitTask {

    return this.server.scheduler.runTaskTimerAsynchronously(this, {
        function()
    }, 0L, millis)

}

fun MCPlugin.repeatAsync(function: () -> Unit, delay: Int, period: Int): BukkitTask {

    return this.server.scheduler.runTaskTimerAsynchronously(this, {
        function()
    }, delay*20L, period * 20L)

}

fun MCPlugin.repeatAsync(function: () -> Unit, delay: Long, period: Long): BukkitTask {

    return this.server.scheduler.runTaskTimerAsynchronously(this, {
        function()
    }, delay, period)

}