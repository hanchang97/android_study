package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {   // LoggerLocalDataSource는 애플리케이션 컨테이너로 범위가 지정되므로 애플리케이션 컨테이너에서 LogDao 결합을 사용할 수 있어야 합니다

    // Kotlin에서 @Provides 함수만 포함하는 모듈은 object 클래스가 될 수 있습니다. 이렇게 하면 제공자가 최적화되고 생성된 코드에 대부분 인라인됩니다.

    @Provides
    @Singleton  // 스코프! -> 계속 동일한 인스턴스를 사용
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase { // 항상 동일한 데이터베이스 인스턴스 제공을 위해 @Singleton 사용
                            // Context가 필요한 경우에 간편하게 사용할 수 있도록 @ApplicatioinContext 가 제공된다
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    fun provideLogDao(database: AppDatabase): LogDao {  // Hilt에게 AppDatabse 타입의 인스턴스도 제공하는 방법을 알려줘야 해서 위 코드 작성
        return database.logDao()
    }

}