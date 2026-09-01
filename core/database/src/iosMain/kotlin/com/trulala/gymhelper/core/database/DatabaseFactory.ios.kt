package com.trulala.gymhelper.core.database

import androidx.room3.Room
import androidx.room3.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual class DatabaseFactory {

    actual fun createBuilder(): RoomDatabase.Builder<GymHelperDatabase> =
        Room.databaseBuilder<GymHelperDatabase>(
            name = documentDirectory() + "/" + GymHelperDatabase.FILE_NAME,
        )

    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val directory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        return requireNotNull(directory?.path) { "Не удалось получить NSDocumentDirectory" }
    }
}
