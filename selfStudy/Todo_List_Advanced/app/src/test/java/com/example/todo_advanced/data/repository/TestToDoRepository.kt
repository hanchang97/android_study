package com.example.todo_advanced.data.repository

import com.example.todo_advanced.data.entity.ToDoEntity

class TestToDoRepository: ToDoRepository {  // 테스트용 repository

    private val todoList: MutableList<ToDoEntity> = mutableListOf()

    override suspend fun getToDoList(): List<ToDoEntity> {
        return todoList
    }

    override suspend fun insertToDoList(toDoList: List<ToDoEntity>) {
        this.todoList.addAll(toDoList)
    }

}