package com.hanchang97.screenonoff

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val br = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      /*  val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(br, filter)*/

        // unregeisterReceiver 를 해줘야 한다!! -> 안 해주면 홈키 눌러서 앱 에서 벗어난 경우  화면 on/off 해도 인식이 된다

        val btn = findViewById<Button>(R.id.btn_custom_broadcast)
        btn.setOnClickListener {
            val intent = Intent("example.broadcast.custom.test")
            intent.setAction("example.broadcast.custom.test").setPackage("com.hanchang97.screenonoff") // setPackage 까지 해야 동작!!!
            sendBroadcast(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        Log.d("MyBroadcastReceiver", "onResume")

        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        //filter.addAction("example.broadcast.custom.test")  //
        registerReceiver(br, filter)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MyBroadcastReceiver", "onPause")

        //unregisterReceiver(br)
    }

    override fun onStop() {
        super.onStop()
        Log.d("MyBroadcastReceiver", "onStop")

        //unregisterReceiver(br)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyBroadcastReceiver", "onDestroy")

        unregisterReceiver(br)
    }
}