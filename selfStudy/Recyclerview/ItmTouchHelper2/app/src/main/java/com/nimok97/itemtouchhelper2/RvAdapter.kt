package com.nimok97.itemtouchhelper2

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nimok97.itemtouchhelper2.databinding.ItemRvBinding

class RvAdapter: ListAdapter<MyData, RvAdapter.MyDataViewHolder>(MyDataDiffUtil) {

    class MyDataViewHolder(private val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData) {
            binding.tvTextView.text = myData.content
            binding.ivImage.load(myData.imgUrl)

            binding.swipeView.isVisible = true

            if(myData.isSwiped == false){
                Log.d("AppTest", "hihi : ${adapterPosition}")
                binding.swipeView.translationX = 0f
            }

            Log.d("AppTest", "bind : ${binding.swipeView == null}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDataViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyDataViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object MyDataDiffUtil : DiffUtil.ItemCallback<MyData>() {
        override fun areItemsTheSame(oldItem: MyData, newItem: MyData) =
            oldItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: MyData, newItem: MyData) =
            oldItem == newItem
    }
}