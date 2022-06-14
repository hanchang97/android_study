package com.hanchang97.itemtouchhelperstudy

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hanchang97.itemtouchhelperstudy.databinding.ItemRvBinding

class RvAdapter(private val longClick: () -> Unit): ListAdapter<MyData, RvAdapter.MyDataViewHolder>(MyDataDiffUtil) {

    inner class MyDataViewHolder(private val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData, longClick: () -> Unit) {
            Log.d("AppTest", "bind")
            binding.clCheckbox.isVisible = myData.isCheckVisible
            binding.tvTextView.text = myData.content
            binding.ivImage.load(myData.imgUrl)

            binding.swipeView.translationX = 0f // 스와이프 된 상태의 뷰가 재활용 될 수 있으므로

            binding.eraseItemView.setOnClickListener {
                if(myData.isClamped) removeItem(adapterPosition)
            }

            binding.root.setOnLongClickListener(View.OnLongClickListener {
                val pos = adapterPosition
                Log.d("AppTest", "long click pos : ${pos}")

                longClick.invoke()

                return@OnLongClickListener true
            })
        }

        fun setClamped(isClamped: Boolean){
            getItem(adapterPosition).isClamped = isClamped
        }

        fun getClamped(): Boolean{
            return getItem(adapterPosition).isClamped
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        holder.bind(getItem(position), longClick)
    }

    private object MyDataDiffUtil : DiffUtil.ItemCallback<MyData>() {

        override fun areItemsTheSame(oldItem: MyData, newItem: MyData) =
            oldItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: MyData, newItem: MyData) =
            oldItem == newItem

    }

    fun removeItem(position: Int){  // currentList에서 바로 아이템 지우면 에러 발생
        val newList = currentList.toMutableList()
        newList.removeAt(position)

        newList.forEach {
            it.isClamped = false
        } // 한 아이템 삭제 시 다른 아이템들 모두 스와이프x 상태 처리하기 위함

        submitList(newList.toList())
    }

    fun makeCheckBosVisible(){
        val newList = ArrayList<MyData>()
        currentList.forEach {
            newList.add(MyData(it.id, it.content, it.imgUrl, it.isClamped, true))
        }
        submitList(newList.toList())
    }

    fun makeCheckBoxGone(){
        val newList = ArrayList<MyData>()
        currentList.forEach {
            newList.add(MyData(it.id, it.content, it.imgUrl, it.isClamped, false))
        }
        submitList(newList.toList())
    }
}