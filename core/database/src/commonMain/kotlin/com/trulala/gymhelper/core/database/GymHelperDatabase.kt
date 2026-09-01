package com.trulala.gymhelper.core.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import androidx.room3.RoomDatabaseConstructor
import com.trulala.gymhelper.core.database.dao.ExerciseDao
import com.trulala.gymhelper.core.database.entity.ExerciseEntity

@Database(
    entities = [ExerciseEntity::class],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(GymHelperDatabaseConstructor::class)
abstract class GymHelperDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        const val FILE_NAME: String = "gym_helper.db"
    }
}

/**
 * actual-реализацию генерирует KSP под каждый таргет — рефлексии в Room 3 нет,
 * поэтому конструктор объявляется явно.
 */
@Suppress("KotlinNoActualForExpect", "NO_ACTUAL_FOR_EXPECT")
expect object GymHelperDatabaseConstructor : RoomDatabaseConstructor<GymHelperDatabase> {
    override fun initialize(): GymHelperDatabase
}
