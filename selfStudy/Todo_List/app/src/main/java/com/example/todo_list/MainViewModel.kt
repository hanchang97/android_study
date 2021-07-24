package com.example.todo_list

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {  // 데이터를 액티비티 단이 아닌 뷰모델에서 관리

    val dataList = arrayListOf<TodoData>()

    fun addTodo(todo: TodoData){  // 리스트 추가
        dataList.add(todo)

    }

    fun deleteTodo(todo : TodoData){ // 삭제 기능
        dataList.remove(todo)

    }

    fun toggleTodo(todo: TodoData){  // isDone을 바꾼다
        todo.isDone = !todo.isDone

    }
}