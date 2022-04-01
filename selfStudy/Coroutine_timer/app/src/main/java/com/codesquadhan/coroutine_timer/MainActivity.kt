package com.codesquadhan.coroutine_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var btnTimer: Button
    private lateinit var tvTimer: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTimer = findViewById(R.id.btn_Timer)
        tvTimer = findViewById(R.id.tv_time)

        btnTimer.setOnClickListener {

        }

    }
}

class TimerThread() : Thread(){
    override fun run() {
        super.run()
    }
}