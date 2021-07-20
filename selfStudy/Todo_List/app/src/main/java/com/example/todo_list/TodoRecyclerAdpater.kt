package com.example.todo_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.ItemTodoBinding

class TodoRecyclerAdpater(private val dataSet: List<TodoData>) :  // 공홈에는 Array<>로 나와있지만 편의를 위해 List로
    RecyclerView.Adapter<TodoRecyclerAdpater.MyViewHolder>() {

    class MyViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {  // 어떤 view 에서 생성된 바인딩 인지 root에 담고 있다
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

        return MyViewHolder(ItemTodoBinding.bind(view))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        myViewHolder.binding.tvTodoContent.text = dataSet[position].text
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
