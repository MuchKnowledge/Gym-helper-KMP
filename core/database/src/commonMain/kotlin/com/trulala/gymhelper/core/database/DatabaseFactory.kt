package com.trulala.gymhelper.core.database

import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

/**
 * Платформа отвечает только за путь к файлу БД; вся остальная настройка — общая,
 * чтобы Android и iOS не разъезжались по драйверу и диспетчеру запросов.
 */
expect class DatabaseFactory {
    fun createBuilder(): RoomDatabase.Builder<GymHelperDatabase>
}

fun DatabaseFactory.createDatabase(): GymHelperDatabase =
    createBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
