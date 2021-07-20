package com.example.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val data = arrayListOf<TodoData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        data.add(TodoData("과제", false))
        data.add(TodoData("은행가기", false))

        mainBinding.recyclerviewTodo.layoutManager = LinearLayoutManager(this)
        mainBinding.recyclerviewTodo.adapter = TodoRecyclerAdpater(data)
        mainBinding.recyclerviewTodo.addItemDecoration(TodoRecyclerViewDecoration(10))


    }
}

// data class Todo(val text : String, var isDone: Boolean)  // var = 수정 가능

// data class -->>  자동으로 getter, setter 구현 / 모델 클래스로 사용