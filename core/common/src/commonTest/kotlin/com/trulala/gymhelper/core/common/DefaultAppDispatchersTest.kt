package com.trulala.gymhelper.core.common

import kotlinx.coroutines.Dispatchers
import kotlin.test.Test
import kotlin.test.assertSame

/**
 * Дымовой тест: доказывает, что commonTest подключён к host-компиляции
 * и kotlin.test запускается. Содержательные тесты появятся вместе с MVI-хелперами.
 */
class DefaultAppDispatchersTest {
    private val dispatchers = DefaultAppDispatchers()

    @Test
    fun `default maps to Dispatchers Default`() {
        assertSame(Dispatchers.Default, dispatchers.default)
    }

    @Test
    fun `io maps to Dispatchers IO`() {
        assertSame(Dispatchers.IO, dispatchers.io)
    }
}
