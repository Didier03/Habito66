package com.example.habito66

import android.app.Application
import com.example.habito66.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Habito66Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@Habito66Application)

            modules(appModules)
        }
    }
}