package com.example.todo_advanced.di

import com.example.todo_advanced.data.repository.TestToDoRepository
import com.example.todo_advanced.data.repository.ToDoRepository
import com.example.todo_advanced.domain.todo.GetToDoListUseCase
import com.example.todo_advanced.domain.todo.InsertToDoListUseCase
import org.koin.dsl.module

internal val appTestModule = module{

    // UseCase
    factory {
        GetToDoListUseCase()
    }
    factory {
        InsertToDoListUseCase()
    }

    // Repository
    single<ToDoRepository>{TestToDoRepository()}
}