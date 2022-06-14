package com.hanchang97.itemtouchhelperstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hanchang97.itemtouchhelperstudy.databinding.ItemRvBinding

class RvAdapter: ListAdapter<MyData, RvAdapter.MyDataViewHolder>(MyDataDiffUtil) {

    inner class MyDataViewHolder(private val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData) {
            binding.tvTextView.text = myData.content
            binding.ivImage.load(myData.imgUrl)
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
        holder.bind(getItem(position))
    }

    private object MyDataDiffUtil : DiffUtil.ItemCallback<MyData>() {

        override fun areItemsTheSame(oldItem: MyData, newItem: MyData) =
            oldItem.id == oldItem.id

        override fun areContentsTheSame(oldItem: MyData, newItem: MyData) =
            oldItem == newItem

    }
}