package com.hanchang97.rangebarstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stfalcon.pricerangebar.RangeBarWithChart
import com.stfalcon.pricerangebar.model.BarEntry
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rangeBarWithChart = findViewById<RangeBarWithChart>(R.id.rangeBarWithChart)

        val array = arrayOf(
            10.0f to  0.0f, 15.0f to  10.0f, 20.0f to 15.0f, 25.0f to 20.0f, 30.0f to 25.0f, 35.0f to 30.0f,
        ).map { BarEntry(it.first, it.second) }


        val arrList = ArrayList<BarEntry>()
        arrList.addAll(array)

        rangeBarWithChart.setEntries(arrList)
    }
}