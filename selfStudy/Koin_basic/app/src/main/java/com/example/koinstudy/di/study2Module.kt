package com.example.koinstudy.di

import com.example.koinstudy.`object`.ClassRoom
import com.example.koinstudy.`object`.Student
import com.example.koinstudy.`object`.Teacher
import org.koin.core.qualifier.named
import org.koin.dsl.module

// internal = 같은 모듈 내에서 볼 수 있다
internal val study2Module = module {
    // 모듈은 주입할 객체들을 묶어주는 역할
    single(named("cl1")) { ClassRoom("class1") }
    single(named("cl2")) { ClassRoom("class2") }

    // Type + 이름  으로 구성되어 구분이 가능해진다!!
}