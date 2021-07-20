package com.example.todo_list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TodoRecyclerViewDecoration(private val verticalGap: Int): RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalGap
    }
}

