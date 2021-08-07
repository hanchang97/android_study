package com.example.todo_advanced

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todo_advanced.di.appTestModule
import com.example.todo_advanced.livedata.LiveDataTestObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
internal class ViewModelTest: KoinTest {  //

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var context: Application

    private val dispatcher = TestCoroutineDispatcher()  // 코루틴 테스트 시 메인 스레드, io스레드 왔다갔다

    @Before
    fun setup(){
        startKoin{
            androidContext(context)
            modules(appTestModule)
        }
        Dispatchers.setMain(dispatcher)
    }

    @After   // 테스트 끝난 후  코루틴 사용할 것이고, 코인 사용을 마무리
    fun tearDown(){
        stopKoin()
        Dispatchers.resetMain()  // MainDispatcher를 초기화 해주어야 메모리 누수가 발생하지 않는다
    }

    // livedata 내부 인스턴스 담기
    protected fun <T> LiveData<T>.test(): LiveDataTestObserver<T>{
        val testObserver = LiveDataTestObserver<T>()
        observeForever(testObserver)
        return testObserver  // 테스트 끝나면 해제 필요
    }
}
