package com.nimok97.markdown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nimok97.markdown.databinding.ActivitySecondBinding
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin

class SecondActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        val markwon = Markwon.builder(this)
            .usePlugin(TablePlugin.create(this))
            .build()

        binding.btnConvert.setOnClickListener {
            val input = binding.etInput.text.toString()

            markwon.setMarkdown(binding.tvMarkdown, input)
        }
    }
}