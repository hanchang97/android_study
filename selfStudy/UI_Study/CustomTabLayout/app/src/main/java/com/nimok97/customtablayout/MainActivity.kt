package com.nimok97.customtablayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nimok97.customtablayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnFirst.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
        }

        binding.btnSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.btnThird.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}