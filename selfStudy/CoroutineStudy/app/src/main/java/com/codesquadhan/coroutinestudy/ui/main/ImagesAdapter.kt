package com.codesquadhan.coroutinestudy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codesquadhan.coroutinestudy.R
import com.codesquadhan.coroutinestudy.databinding.ItemImagesBinding
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse

class ImagesAdapter: ListAdapter<ImageSearchResponse, ImagesAdapter.MyViewHolder>(comparator){

    class MyViewHolder(val binding: ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageObject: ImageSearchResponse) {  // Glide도 자체적으로 화면이 보이지 않는 부분은 로딩을 cancel 한다
            Glide.with(binding.root).load(imageObject.urls.regular)
                .into(binding.ivImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)

        var params = view.layoutParams
        params.width = parent.measuredWidth / 3    // 아이템뷰의 크기를 리사이클러뷰 크기 대비 비율로 지정이 가능
        params.height = params.width

        return MyViewHolder(ItemImagesBinding.bind(view))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object{
        val comparator = object : DiffUtil.ItemCallback<ImageSearchResponse>() {
            override fun areItemsTheSame(oldItem: ImageSearchResponse, newItem: ImageSearchResponse): Boolean {  // id, 주민번호 등 특징적인 것으로 비교
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageSearchResponse, newItem: ImageSearchResponse): Boolean {
                return oldItem == newItem  // 내용들이 같은지 체크,  java의 equals !!
            }

            // areItemsTheSame 이 참이면 areContentsTheSame 가 호출된다
        }
    }


}