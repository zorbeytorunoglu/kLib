package com.zorbeytorunoglu.kLib.task

import com.zorbeytorunoglu.kLib.MCPlugin
import kotlinx.coroutines.*

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