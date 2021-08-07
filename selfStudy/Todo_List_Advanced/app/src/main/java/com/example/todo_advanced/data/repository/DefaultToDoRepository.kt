package com.example.todo_advanced.data.repository

import com.example.todo_advanced.data.entity.ToDoEntity

class DefaultToDoRepository: ToDoRepository {
    override suspend fun getToDoList(): List<ToDoEntity> {

    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {

    }

}