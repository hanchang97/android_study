package com.hanchang97.coroutinescope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intFlow: Flow<Int> = flow {
            for (i in 1..20) {
                emit(i)
                delay(1000)
            }
        }

        GlobalScope.launch {
            intFlow.collect {
                Log.d("AppTest", "intFlow1 : ${it}")
            }
        }

        lifecycle.coroutineScope.launch {
            intFlow.collect {
                Log.d("AppTest", "intFlow2 : ${it}")
            }
        }

        lifecycleScope.launch {
            intFlow.collect {
                Log.d("AppTest", "intFlow3 : ${it}")
            }
        }


        lifecycleScope.launchWhenStarted {
            intFlow.collect {
                Log.d("AppTest", "intFlow4 : ${it}")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("AppTest", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AppTest", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AppTest", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AppTest", "onDestory called")
    }
}