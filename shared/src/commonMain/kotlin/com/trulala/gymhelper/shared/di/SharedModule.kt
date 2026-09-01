package com.trulala.gymhelper.shared.di

import com.trulala.gymhelper.core.common.AppDispatchers
import com.trulala.gymhelper.core.common.DefaultAppDispatchers
import com.trulala.gymhelper.core.database.di.databaseModule
import org.koin.dsl.module

/**
 * Сборка общего графа. Модули фич будут добавляться в [sharedModules] списком,
 * чтобы платформенные точки входа знали только про него.
 */
val coreModule = module {
    single<AppDispatchers> { DefaultAppDispatchers() }
}

val sharedModules = listOf(
    coreModule,
    databaseModule,
)
