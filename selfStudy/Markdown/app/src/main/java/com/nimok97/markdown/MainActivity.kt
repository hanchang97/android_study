package com.nimok97.markdown

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nimok97.markdown.databinding.ActivityMainBinding
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //val markwon = Markwon.create(this)

        // 테이블 사용하려면 아래와 같이!
        val markwon = Markwon.builder(this)
            .usePlugin(TablePlugin.create(this))
            .build()

        markwon.setMarkdown(binding.tvTest, "# Hello\n - hihi\n - byebye" +
                "\n\n" +
                "Markdown | Less | Pretty\n--- | --- | ---\n*Still* | `renders` | **nicely**\n1 | 2 | 3")



        binding.btnSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}