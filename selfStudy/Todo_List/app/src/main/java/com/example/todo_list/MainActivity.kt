package com.example.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()   //activity-ktx

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        /*dataList.add(TodoData("과제", false))
        dataList.add(TodoData("은행가기", false))*/

        mainBinding.recyclerviewTodo.layoutManager = LinearLayoutManager(this)
        mainBinding.recyclerviewTodo.adapter = TodoRecyclerAdpater(
                emptyList(),
                onClickDeleteIcon = {
                    mainViewModel.deleteTodo(it)
                    //mainBinding.recyclerviewTodo.adapter?.notifyDataSetChanged()
                },
                onClickItem = {
                    mainViewModel.toggleTodo(it)
                    //mainBinding.recyclerviewTodo.adapter?.notifyDataSetChanged()
                }
            )

        mainBinding.recyclerviewTodo.addItemDecoration(TodoRecyclerViewDecoration(15)) // 리사이클러뷰 아이템 간 간격 조절

        mainBinding.btnAdd.setOnClickListener {
            val todo = TodoData(mainBinding.etTodo.text.toString())

            mainViewModel.addTodo(todo)
            //mainBinding.recyclerviewTodo.adapter?.notifyDataSetChanged()
        }

        //관찰 - UI 업데이트   //  첫번째 인자는 액티비티 자체
        mainViewModel.todoLiveData.observe(this, Observer {
            // livedata 에 저장되는 값 타입이 it
            // 내가 만든 어댑터 클래스로 캐스팅!
            (mainBinding.recyclerviewTodo.adapter as TodoRecyclerAdpater).setData(it)
        })
    }

//    fun addTodo(){  // 리스트 추가
//        val todo = TodoData(mainBinding.etTodo.text.toString())
//        dataList.add(todo)
//        mainBinding.recyclerviewTodo.adapter?.notifyDataSetChanged()
//    }
//
//    fun deleteTodo(todo : TodoData){ // 삭제 기능
//        dataList.remove(todo)
//        mainBinding.recyclerviewTodo.adapter?.notifyDataSetChanged()
//    }
//
//    fun toggleTodo(todo: TodoData){  // isDone을 바꾼다
//        todo.isDone = !todo.isDone
//        mainBinding.recyclerviewTodo.adapter?.notifyDataSetChanged()
//    }
}

// data class Todo(val text : String, var isDone: Boolean)  // var = 수정 가능

// data class -->>  자동으로 getter, setter 구현 / 모델 클래스로 사용