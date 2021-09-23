package com.example.shoppingapp

import android.app.Application
import com.example.shoppingapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()   // Application name 매니페스트 가서 설정해주기
                          // Application onCreate 시점에 앱 모듈 주입

        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}