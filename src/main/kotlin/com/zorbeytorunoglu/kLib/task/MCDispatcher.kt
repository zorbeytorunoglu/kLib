package com.zorbeytorunoglu.kLib.task

import com.zorbeytorunoglu.kLib.MCPlugin
import kotlinx.coroutines.*
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
class MCDispatcher(private val plugin: MCPlugin, private val async: Boolean = false) : CoroutineDispatcher(), Delay {

    private val mcDispatcher
        get() = Bukkit.getScheduler()

    private val runTaskLater: (Plugin, Runnable, Long) -> BukkitTask =
        if (async)
            mcDispatcher::runTaskLaterAsynchronously
        else
            mcDispatcher::runTaskLater
    private val runTask: (Plugin, Runnable) -> BukkitTask =
        if (async)
            mcDispatcher::runTaskAsynchronously
        else
            mcDispatcher::runTask

    @ExperimentalCoroutinesApi
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val task = runTaskLater(
            plugin,
            Runnable {
                continuation.apply { resumeUndispatched(Unit) }
            },
            timeMillis / 50)
        continuation.invokeOnCancellation { task.cancel() }
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if (!context.isActive) {
            return
        }

        if (!async && Bukkit.isPrimaryThread()) {
            block.run()
        } else {
            runTask(plugin, block)
        }
    }

}

fun MCPlugin.dispatcher(async: Boolean = false) = MCDispatcher(this, async)