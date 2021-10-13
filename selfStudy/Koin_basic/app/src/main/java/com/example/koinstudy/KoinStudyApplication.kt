package com.example.koinstudy

import android.app.Application
import com.example.koinstudy.`object`.Student
import com.example.koinstudy.`object`.Teacher
import com.example.koinstudy.di.study1Module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinStudyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        // 의존성 주입을 위해서는 Container 필요

        // 안드로이드에서 컨테이너는 안드로이드 컴포넌트의 Life cycle에 맞추어 생성과 파괴가 되도록 만들어져야 한다
        // androidContext 메서드를 통해 설정

        startKoin {
            androidContext(this@KoinStudyApplication)  // KoinStudyApplication이 끝날 때 이 Container 또한 끝
            modules(
                // 모듈 시작
                module {
                    single { Teacher("teacher_Park1") }  // single - 하나만 생성되고 재활용되는 Singleton Object 만들기
                    factory { Student("student_Kim1") } // factory - 매번 새로 생성되는 Factory Object 만들기
                }
                // 모듈 종료
//                ,
  //              module{   // 모듈이 여러개라면 이렇게 추가해주면 된다

    //            }

            )
            //modules(study1Module)   //  modules 자체를 여러개로도 가능  / 주석 해제하면 오류 why??? ->
            //Teacher, Stduent 객체 주입 선언해서 또 하면 오류 발생!!
        }
    }
}

