package com.example.busschedule.di

import android.content.Context
import androidx.room.Room
import com.example.busschedule.database.AppDatabase
import com.example.busschedule.database.schedule.ScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    @Singleton  // 스코프! -> 계속 동일한 인스턴스를 사용
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase { // 항상 동일한 데이터베이스 인스턴스 제공을 위해 @Singleton 사용
        // Context가 필요한 경우에 간편하게 사용할 수 있도록 @ApplicatioinContext 가 제공된다
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).createFromAsset("database/bus_schedule.db").build()
    }

    @Provides
    fun provideLogDao(database: AppDatabase): ScheduleDao {  // Hilt에게 AppDatabse 타입의 인스턴스도 제공하는 방법을 알려줘야 해서 위 코드 작성
        return database.scheduleDao()
    }
}