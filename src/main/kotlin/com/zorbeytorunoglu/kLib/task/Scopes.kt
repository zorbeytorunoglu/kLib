package com.zorbeytorunoglu.kLib.task

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.SupervisorJob

object Scopes {

    val supervisorScope: CoroutineScope = CoroutineScope(SupervisorJob())
    val ioScope: CoroutineScope = CoroutineScope(IO)
    val defaultScope: CoroutineScope = CoroutineScope(Default)

}