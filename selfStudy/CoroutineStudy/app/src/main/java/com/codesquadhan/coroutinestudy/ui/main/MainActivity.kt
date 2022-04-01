package com.codesquadhan.coroutinestudy.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.codesquadhan.coroutinestudy.R
import com.codesquadhan.coroutinestudy.common.RetrofitBuilder
import com.codesquadhan.coroutinestudy.databinding.ActivityMainBinding

const val TAG = "AppTest"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImageSearchViewModel by viewModels()

    private lateinit var adpater: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRv()
        setBtn()
        setImages()
    }

    fun setRv(){
        adpater = ImagesAdapter()
        binding.recycelrView.adapter = adpater
        binding.recycelrView.layoutManager = GridLayoutManager(this, 3)
    }

    fun setBtn(){
        binding.btnSearch.setOnClickListener {
            val query = binding.editText.text.toString()
            viewModel.searchImage(query)
        }
    }

    fun setImages(){
        viewModel.images.observe(this){
            Log.d(TAG, "${it.size}")
            adpater.submitList(it.toList())
        }
    }


}