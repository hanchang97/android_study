package com.example.android.hilt.di

import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerInMemoryDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class InMemoryLogger    // 구분을 위한 Qualifier 추가!!

@Qualifier
annotation class DatabaseLogger


@InstallIn(SingletonComponent::class)
@Module
abstract class LoggingDatabaseModule {

    @DatabaseLogger // Qualifier 사용해주기
    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(impl: LoggerLocalDataSource): LoggerDataSource
}

@InstallIn(ActivityComponent::class)
@Module
abstract class LoggingInMemoryModule {

    @InMemoryLogger
    @ActivityScoped   // Module 에서 Install 한 범위를 벗어날 수 없다!!
    @Binds
    abstract fun bindInMemoryLogger(impl: LoggerInMemoryDataSource): LoggerDataSource  // LoggerInMemoryDataSource 에서 객체를 생성해야 한다고 알려주는 것!!!
}


// 같은 타입이 여러 개 사용된 경우, Hilt에게 어떻게 알려줘야 할까??

// -> Qualifier 사용하기!!!

