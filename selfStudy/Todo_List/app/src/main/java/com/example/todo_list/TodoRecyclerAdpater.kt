package com.example.todo_list

import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.ItemTodoBinding   // ctrl + alt + l = 코드정리

class TodoRecyclerAdpater(
    private var dataSet: List<TodoData>,
    val onClickDeleteIcon: (todo: TodoData) -> Unit,    // Unit = java 에서 void 느낌이라 생각!!
    val onClickItem: (todo: TodoData) -> Unit   // 함수 전달
) :  // 공홈에는 Array<>로 나와있지만 편의를 위해 List로
    RecyclerView.Adapter<TodoRecyclerAdpater.MyViewHolder>() {

    class MyViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {  // 어떤 view 에서 생성된 바인딩 인지 root에 담고 있다
        //val myTextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            // myTextView = view.findViewById(R.id.tv_todo_content)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo, viewGroup, false)


        // itemview 비율로 높이 설정하기
        var params = view.layoutParams  // 아이템 뷰
        params.height = viewGroup.measuredHeight / 6;  // 아이템 뷰의 높이를 리사이클러 뷰가 차지하는 영역의 1/6 로 설정
        //

        return MyViewHolder(ItemTodoBinding.bind(view))
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {  //아이템 뷰 어떻게 생겼는지 결정

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val todo = dataSet[position]
        myViewHolder.binding.tvTodoContent.text = dataSet[position].text

        // 할일 목록 삭제
        myViewHolder.binding.ivDelete.setOnClickListener {
            onClickDeleteIcon.invoke(todo)  // 지울 아이템 전달
        }

        // 할일 완료 시 밑줄 치기
        if(todo.isDone){
           /* myViewHolder.binding.tvTodoContent.paintFlags =
                    myViewHolder.binding.tvTodoContent.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG */  // 아래와 같이 간단하게 만들 수 있다
            myViewHolder.binding.tvTodoContent.apply{
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG //  밑줄
                setTypeface(null, Typeface.ITALIC) // 글씨체 변경
            }
        }else{
            myViewHolder.binding.tvTodoContent.apply{
                paintFlags = 0 // 0 = 기존 일반 글자
                setTypeface(null, Typeface.NORMAL)
            }
        }


        // 아이템 뷰 클릭 시 할일 완료/미완료 처리
        myViewHolder.binding.root.setOnClickListener {
            onClickItem.invoke(todo)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    // 데이터 갱신을 위함
    fun setData(newData: List<TodoData>){
        dataSet = newData
        notifyDataSetChanged()   // setData 호출 하면 화면 갱신되게 하기 위해 notify 호출
    }

}
