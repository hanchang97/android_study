package com.example.todo_advanced

import android.app.Application
import com.example.todo_advanced.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // TODO Koin Trigger
        startKoin {
            androidLogger(Level.ERROR)  // 에러 발생시 로깅
            androidContext(this@TodoApplication)
            modules(appModule)
        }
    }
}