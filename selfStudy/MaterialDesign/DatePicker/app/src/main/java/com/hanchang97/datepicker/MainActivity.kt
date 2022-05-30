package com.hanchang97.datepicker

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.hanchang97.datepicker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val dateRangePicker =
        MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("여행 일정")
            //.setTheme(R.style.ThemeOverlay_App_DatePicker)
            //.setInputMode(INPUT_MODE_TEXT)
            /*.setSelection(
                Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
            )*/
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        dateRangePicker.addOnPositiveButtonClickListener {
            Log.d("AppTest", "Select Date : ${convertLongToDate(it.first)} ~ ${convertLongToDate(it.second)}")
        }

  /*      dateRangePicker.dialog?.window?.setLayout(300,300)
        dateRangePicker.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dateRangePicker.dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)*/

        dateRangePicker.show(supportFragmentManager, "datepicker")


        binding.btnGotoSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun convertLongToDate(time: Long): String{
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

        return format.format(date)
    }
}