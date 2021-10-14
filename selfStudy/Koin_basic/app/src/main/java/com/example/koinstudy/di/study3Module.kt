package com.example.koinstudy.di

import com.example.koinstudy.`object`.*
import org.koin.core.qualifier.named
import org.koin.dsl.module

// internal = 같은 모듈 내에서 볼 수 있다
internal val study3Module = module {

    // 제공된 객체가 다른 객체의 생성자에 주입되어야 하는 경우!!

    // Computer 클래스는 Cpu 클래스를 주입받아야 한다

    //single{ Cpu("cpu1") }
    //single{ Computer("computer1", get()) }

    // get 또한 named 를 통해서 두 개 이상의 cpu가 있을 시 어떤 cpu를 주입할지를 결정할 수 있다
    single(named("cpu100")){Cpu("cpu100")}
    single(named("cpu200")){Cpu("cpu200")}
    single{Computer("computer100", get(named("cpu200")))}

}