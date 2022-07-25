package com.nimok97.menuandpopup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuBuilder
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

    // v = anchor View -> 메뉴가 v 기준으로 생성된다!!
    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val wrapper = ContextThemeWrapper(this, R.style.CustomPopup) // 스타일 적용을 위함
        //val popup = PopupMenu(this, v)
        val popup = PopupMenu(wrapper, v)

        popup.menuInflater.inflate(menuRes, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            Log.d("AppTest", "${menuItem}")
            menuItem.isChecked = true
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


        // checked
        popup.menu.getItem(0).isChecked = true

        // Show the popup menu.
        popup.show()
    }
}