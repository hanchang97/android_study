package com.hanchang97.composewithview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var btnGotoCompose: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGotoCompose = findViewById(R.id.btn_goto_compose)
        btnGotoCompose.setOnClickListener {
            startActivity(Intent(this, ComposeActivity::class.java))
        }
    }
}