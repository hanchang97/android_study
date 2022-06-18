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

            // 뷰홀더 재사용 과정에서 isClamped 값에 맞지 않는 스와이프 상태가 보일 수 있으므로 아래와 같이 명시적으로 isClamped 값에 따라 스와이프 상태 지정
            if(myData.isClamped) binding.swipeView.translationX = binding.root.width * -1f / 10 * 3
            else binding.swipeView.translationX = 0f

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