package com.example.todo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {  // 데이터를 액티비티 단이 아닌 뷰모델에서 관리

    val todoLiveData = MutableLiveData<List<TodoData>>() // 수정도 가능한 라이브데이터

    private val dataList = arrayListOf<TodoData>()  // 외부에서 수정 불가

    fun addTodo(todo: TodoData){  // 리스트 추가
        dataList.add(todo)
        todoLiveData.value = dataList // 할일 목록 추가 시 최신데이터로 변경
    }

    fun deleteTodo(todo : TodoData){ // 삭제 기능
        dataList.remove(todo)
        todoLiveData.value = dataList // 데이터 변경 마다 업데이트
    }

    fun toggleTodo(todo: TodoData){  // isDone을 바꾼다
        todo.isDone = !todo.isDone
        todoLiveData.value = dataList
    }
}