package com.nimok97.contextualactionbar

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.databinding.DataBindingUtil
import com.nimok97.contextualactionbar.databinding.ActivityMainBinding
import com.nimok97.contextualactionbar.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity(){

    private lateinit var binding: ActivitySecondBinding
    private var actionMode: ActionMode? = null

    private var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        setSupportActionBar(binding.toolbar)

        val callback = object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("AppTest", "onCreateActionMode")
                //supportActionBar?.let {  it.hide()}
                menuInflater.inflate(R.menu.contextual_action_bar, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                Log.d("AppTest", "onPrepareActionMode")
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.share -> {
                        // Handle share icon press
                        true
                    }
                    R.id.delete -> {
                        // Handle delete icon press
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                Log.d("AppTest", "onDestroyActionMode")
                //supportActionBar?.let {  it.show()}
                actionMode = null
            }
        }


        // 버튼 클릭 시 액션모드 활성화
        binding.btnActionMode.setOnClickListener {
            actionMode = startSupportActionMode(callback)
            actionMode?.title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                Html.fromHtml("<font color='#0000FF'>ActionBarTitle ${count}</font>", Html.FROM_HTML_MODE_LEGACY)
            else
                Html.fromHtml("<font color='#0000FF'>ActionBarTitle ${count}</font>")
        }

        binding.btnCount.setOnClickListener {
            count++
            actionMode?.title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                Html.fromHtml("<font color='#0000FF'>ActionBarTitle ${count}</font>", Html.FROM_HTML_MODE_LEGACY)
            else
                Html.fromHtml("<font color='#0000FF'>ActionBarTitle ${count}</font>")
        }
    }
}