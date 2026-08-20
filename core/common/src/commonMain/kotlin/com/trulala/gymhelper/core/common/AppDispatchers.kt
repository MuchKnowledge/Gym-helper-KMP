package com.trulala.gymhelper.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Диспетчеры за интерфейсом, чтобы в тестах подменять их на [kotlinx.coroutines.test.StandardTestDispatcher]
 * без глобального Dispatchers.setMain и без завязки на платформу.
 */
interface AppDispatchers {
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
}

class DefaultAppDispatchers : AppDispatchers {
    override val main: CoroutineDispatcher get() = Dispatchers.Main
    override val default: CoroutineDispatcher get() = Dispatchers.Default
    override val io: CoroutineDispatcher get() = Dispatchers.IO
}
