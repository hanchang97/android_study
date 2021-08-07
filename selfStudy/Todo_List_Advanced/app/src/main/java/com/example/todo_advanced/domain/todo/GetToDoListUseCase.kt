package com.example.todo_advanced.domain.todo

import com.example.todo_advanced.data.repository.ToDoRepository
import com.example.todo_advanced.domain.UseCase

class GetToDoListUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {



}