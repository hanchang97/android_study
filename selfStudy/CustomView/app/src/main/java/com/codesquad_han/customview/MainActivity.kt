package com.codesquad_han.customview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.codesquad_han.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGotoActivity2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d("AppTest", "onProgressChanged/ ${progress}, ${fromUser}" )
            }

            override fun onStartTrackingTouch(seekbar: SeekBar?) {
                Log.d("AppTest", "onStartTrackingTouch/ ${seekbar!!.progress}" )
            }

            override fun onStopTrackingTouch(seekbar: SeekBar?) {
                Log.d("AppTest", "onStopTrackingTouch/ ${seekbar!!.progress}" )
            }

        })
    }
}