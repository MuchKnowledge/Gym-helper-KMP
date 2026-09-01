package com.trulala.gymhelper.core.database

import androidx.room3.Room
import androidx.room3.RoomDatabase
import java.io.File

/**
 * JVM-таргет существует ради DAO-тестов (см. gymhelper.kmp.room), но expect/actual
 * обязывает дать реализацию и для main. Кладём файл рядом с домашней директорией —
 * если когда-нибудь появится desktop-запуск, путь честно рабочий.
 */
actual class DatabaseFactory {
    actual fun createBuilder(): RoomDatabase.Builder<GymHelperDatabase> {
        val dbFile = File(System.getProperty("user.home"), ".gymhelper/${GymHelperDatabase.FILE_NAME}")
        dbFile.parentFile?.mkdirs()
        return Room.databaseBuilder<GymHelperDatabase>(name = dbFile.absolutePath)
    }
}
