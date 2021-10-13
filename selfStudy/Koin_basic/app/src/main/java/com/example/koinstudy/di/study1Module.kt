package com.example.koinstudy.di

import com.example.koinstudy.`object`.Student
import com.example.koinstudy.`object`.Teacher
import org.koin.dsl.module

// internal = 같은 모듈 내에서 볼 수 있다
internal val study1Module = module {
    // 모듈은 주입할 객체들을 묶어주는 역할
    single { Teacher("teacher_Park1") }  // single - 하나만 생성되고 재활용되는 Singleton Object 만들기
    factory { Student("student_Kim2") } // factory - 매번 새로 생성되는 Factory Object 만들기


}