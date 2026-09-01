package com.trulala.gymhelper.core.database.di

import com.trulala.gymhelper.core.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformDatabaseModule(): Module = module {
    single { DatabaseFactory() }
}
