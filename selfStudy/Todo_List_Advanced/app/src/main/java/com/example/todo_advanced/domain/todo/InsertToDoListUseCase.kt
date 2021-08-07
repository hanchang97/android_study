package com.example.todo_advanced.domain.todo

import com.example.todo_advanced.data.repository.ToDoRepository
import com.example.todo_advanced.domain.UseCase

internal class InsertToDoListUseCase(
    private val toDoRepository: ToDoRepository   // ch.Advance.09  14분 까지 들음 현재
): UseCase {

}