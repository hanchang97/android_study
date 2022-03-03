package com.codesquad_han.customview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codesquad_han.customview.databinding.ActivityMainBinding
import com.codesquad_han.customview.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}