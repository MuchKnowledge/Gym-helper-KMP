package com.trulala.gymhelper.shared.di

import com.trulala.gymhelper.core.common.AppDispatchers
import com.trulala.gymhelper.core.common.DefaultAppDispatchers
import org.koin.dsl.module

/**
 * Сборка общего графа. Модули фич будут добавляться сюда списком,
 * чтобы платформенные точки входа знали только про [sharedModules].
 */
val coreModule = module {
    single<AppDispatchers> { DefaultAppDispatchers() }
}

val sharedModules = listOf(coreModule)
