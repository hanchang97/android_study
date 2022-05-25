package com.hanchang97.datepicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("여행 일정")
                .setTheme(R.style.ThemeOverlay_App_DatePicker)
                //.setInputMode(INPUT_MODE_TEXT)
                /*.setSelection(
                    Pair(
                        MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds()
                    )
                )*/
                .build()

        dateRangePicker.show(supportFragmentManager, "datepicker")

        dateRangePicker.addOnPositiveButtonClickListener {
            Log.d("AppTest", "Select Date : ${convertLongToDate(it.first)} ~ ${convertLongToDate(it.second)}")
        }



        binding.btnGotoSecond.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun convertLongToDate(time: Long): String{
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

        return format.format(date)
    }
}