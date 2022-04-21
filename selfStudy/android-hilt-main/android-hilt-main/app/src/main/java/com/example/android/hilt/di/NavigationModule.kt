package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator  // AppNavigatorImpl 로 부터 생성하는 거라고 Hilt에게 알려주는 것
    // 어떤 구현체를 사용할 지 알려주는 것!!!

    // AppNavigatorImpl 의 생성 방법도 Hilt에게 알려줘야 한다 -> AppNavigatorImpl 클래스에 Inject 선언하기
}