package com.trulala.gymhelper.core.database

import android.content.Context
import androidx.room3.Room
import androidx.room3.RoomDatabase

actual class DatabaseFactory(private val context: Context) {

    actual fun createBuilder(): RoomDatabase.Builder<GymHelperDatabase> {
        val dbFile = context.getDatabasePath(GymHelperDatabase.FILE_NAME)
        return Room.databaseBuilder<GymHelperDatabase>(
            context = context.applicationContext,
            name = dbFile.absolutePath,
        )
    }
}
