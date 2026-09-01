package com.trulala.gymhelper.core.database.di

import com.trulala.gymhelper.core.database.DatabaseFactory
import com.trulala.gymhelper.core.database.GymHelperDatabase
import com.trulala.gymhelper.core.database.createDatabase
import com.trulala.gymhelper.core.database.dao.ExerciseDao
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * [platformDatabaseModule] отдаёт DatabaseFactory: на Android ей нужен Context,
 * на iOS — ничего, поэтому создание фабрики разведено по платформам,
 * а сама БД и DAO собираются общим кодом.
 */
expect fun platformDatabaseModule(): Module

val databaseModule: Module = module {
    includes(platformDatabaseModule())

    single<GymHelperDatabase> { get<DatabaseFactory>().createDatabase() }
    single<ExerciseDao> { get<GymHelperDatabase>().exerciseDao() }
}
