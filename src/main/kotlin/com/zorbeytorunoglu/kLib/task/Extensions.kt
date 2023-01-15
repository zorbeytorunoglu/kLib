package com.zorbeytorunoglu.kLib.task

import com.zorbeytorunoglu.kLib.MCPlugin
import kotlinx.coroutines.launch

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