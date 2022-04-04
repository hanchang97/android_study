package com.codesquadhan.coroutinestudy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codesquadhan.coroutinestudy.R
import com.codesquadhan.coroutinestudy.databinding.ItemImagesBinding
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse

class ImagesPagingAdapter : PagingDataAdapter<ImageSearchResponse, ImagesPagingAdapter.ImageViewHolder>(diffUtil) {

    // 사용자가 로드된 데이터의 끝까지 스크롤할 때 어댑터가 자동으로 다음 데이터를 요청
    // paging 라이브러에서 제공해주는 PaingData를 나타내주기 위한 어댑터

    // PagingDataAdapter에 리스너를 등록하여 현재 상태에 관한 정보를 수신하고 그에 따라 UI를 업데이트할 수 있다

    class ImageViewHolder(val binding: ItemImagesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageObject: ImageSearchResponse?) {  // Glide도 자체적으로 화면이 보이지 않는 부분은 로딩을 cancel 한다
            imageObject?.let {
                Glide.with(binding.root).load(imageObject.urls.regular)
                    .into(binding.ivImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)

        var params = view.layoutParams
        params.width = parent.measuredWidth / 3    // 아이템뷰의 크기를 리사이클러뷰 크기 대비 비율로 지정이 가능
        params.height = params.width

        return ImageViewHolder(ItemImagesBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ImageSearchResponse>() {
            override fun areItemsTheSame(
                oldItem: ImageSearchResponse,
                newItem: ImageSearchResponse
            ): Boolean {  // id, 주민번호 등 특징적인 것으로 비교
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImageSearchResponse,
                newItem: ImageSearchResponse
            ): Boolean {
                return oldItem == newItem  // 내용들이 같은지 체크,  java의 equals !!
            }
            // areItemsTheSame 이 참이면 areContentsTheSame 가 호출된다
        }
    }
}