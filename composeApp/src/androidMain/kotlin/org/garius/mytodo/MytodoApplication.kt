package org.garius.mytodo

import android.app.Application
import org.garius.mytodo.app.di.initKoin
import org.koin.android.ext.koin.androidContext

class MytodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MytodoApplication)
        }
    }
}