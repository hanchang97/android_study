package com.example.todo_advanced.data.repository

import com.example.todo_advanced.data.entity.ToDoEntity


interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun insertToDoList(toDoList: List<ToDoEntity>)

}