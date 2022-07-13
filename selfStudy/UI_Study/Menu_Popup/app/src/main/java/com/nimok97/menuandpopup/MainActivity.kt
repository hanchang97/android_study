package com.nimok97.menuandpopup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import com.nimok97.menuandpopup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setBtnFirst()
    }

    private fun setBtnFirst() {
        binding.btnFirst.setOnClickListener {
            showMenu(it, R.menu.menu1)
        }
    }

    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            Log.d("AppTest", "${menuItem}")
            true
        }

        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }

        popup.menu.getItem(0).title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(
                "<font color='#FFFFFF'>option1</font>",
                Html.FROM_HTML_MODE_LEGACY
            )
        else
            Html.fromHtml("<font color='#FFFFFF'>option1</font>")

        ///
        popup.menu.getItem(1).title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(
                "<font color='#74869B'>option2</font>",
                Html.FROM_HTML_MODE_LEGACY
            )
        else
            Html.fromHtml("<font color='#74869B'>option2</font>")

        ///
        popup.menu.getItem(2).title = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(
                "<font color='#74869B'>option3</font>",
                Html.FROM_HTML_MODE_LEGACY
            )
        else
            Html.fromHtml("<font color='#74869B'>option3</font>")


        // Show the popup menu.
        popup.show()
    }
}