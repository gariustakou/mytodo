package org.garius.mytodo.app.di

import org.garius.mytodo.todo.data.database.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
       // single<HttpClientEngine> { OkHttp.create() }
        single { DatabaseFactory() }
    }