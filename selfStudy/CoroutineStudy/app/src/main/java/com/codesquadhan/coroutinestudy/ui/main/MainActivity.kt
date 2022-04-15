package com.codesquadhan.coroutinestudy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.codesquadhan.coroutinestudy.data.repository.ImageSearchRepository
import com.codesquadhan.coroutinestudy.databinding.ActivityMainBinding
import com.codesquadhan.coroutinestudy.ui.main.viewModel.ImageSearchViewModel
import com.codesquadhan.coroutinestudy.ui.main.viewModel.ImageSearchViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val TAG = "AppTest"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageSearchViewModel

    private lateinit var adpater: ImagesAdapter
    private lateinit var pagingAdapter: ImagesPagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageSearchRepository = ImageSearchRepository()
        val imageSearchviewModelFactory = ImageSearchViewModelFactory(imageSearchRepository)
        viewModel = ViewModelProvider(this, imageSearchviewModelFactory).get(ImageSearchViewModel::class.java)

        setRv()
        setBtn()
        setImages()
    }

    fun setRv(){
        adpater = ImagesAdapter()
        pagingAdapter = ImagesPagingAdapter()

        binding.recycelrView.adapter = pagingAdapter
        binding.recycelrView.layoutManager = GridLayoutManager(this, 2)

        pagingAdapter.addLoadStateListener { combinedLoadStates ->
            binding.progressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading

            if(combinedLoadStates.source.refresh is LoadState.Error){
                Log.d("AppTest", "loading error")
            }
        }
    }

    fun setBtn(){
        binding.btnSearch.setOnClickListener {
            val searchQuery = binding.editText.text.toString()
            viewModel.sendSearchQuery(searchQuery)
        }
    }

    fun setImages(){
        /*viewModel.images.observe(this){
            Log.d(TAG, "${it.size}")
            adpater.submitList(it.toList())
        }*/

        lifecycleScope.launch {
            viewModel.imageFlow
                .collectLatest { imageResponse ->
                    pagingAdapter.submitData(imageResponse)   // imageResponse = PagingData<ImageSearchResponse>
                }
        }


    }


}