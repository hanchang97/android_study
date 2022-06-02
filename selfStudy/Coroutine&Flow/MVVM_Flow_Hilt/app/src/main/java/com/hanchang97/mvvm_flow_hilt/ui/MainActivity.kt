package com.hanchang97.mvvm_flow_hilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanchang97.mvvm_flow_hilt.R
import com.hanchang97.mvvm_flow_hilt.databinding.ActivityMainBinding
import com.hanchang97.mvvm_flow_hilt.ui.adapter.PostRecyclerViewAdapter
import com.hanchang97.mvvm_flow_hilt.util.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postRecyclerViewAdapter: PostRecyclerViewAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setRv()
        setPostCollect()

        viewModel.getPost()
    }

    private fun setRv(){
        postRecyclerViewAdapter = PostRecyclerViewAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postRecyclerViewAdapter
        }
    }

    private fun setPostCollect(){
        
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.postStateFlow.collect{ it ->
                    when(it){
                        is ApiState.Loading -> {
                            binding.recyclerview.isVisible=false
                            binding.progressBar.isVisible=true
                            Log.d("AppTest", "load data started")
                        }
                        is ApiState.Error -> {
                            binding.recyclerview.isVisible = false
                            binding.progressBar.isVisible = false
                            Log.d("AppTest", "load data Error, ${it.message}")
                        }
                        is ApiState.Success -> {
                            binding.recyclerview.isVisible = true
                            binding.progressBar.isVisible = false
                            postRecyclerViewAdapter.submitList(it.data)
                            Log.d("AppTest", "load data success, ${it.data.size}")
                            log("MainActivity, flow success")
                        }
                        is ApiState.Empty -> {

                        }
                    }
                }
            }
        }
    }

    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
}