package com.hanchang97.rangebarstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.textfield.TextInputEditText
import com.stfalcon.pricerangebar.RangeBarWithChart
import com.stfalcon.pricerangebar.model.BarEntry
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rangeBarWithChart = findViewById<RangeBarWithChart>(R.id.rangeBarWithChart)

        val etLeftPin = findViewById<TextInputEditText>(R.id.tiet_leftpin)
        val etRightPin = findViewById<TextInputEditText>(R.id.tiet_rightpin)

        val priceList = ArrayList<Int>()
        priceList.add(18000)
        priceList.add(33000)
        priceList.add(15000)
        priceList.add(45000)
        priceList.add(20000)
        priceList.add(37000)
        priceList.add(37000)
        priceList.add(22000)
        priceList.add(26000)

        val chartList = ArrayList<BarEntry>()

        var point = 0f


      /*  priceList.forEach {
            chartList.add(BarEntry(point, 0f))
            chartList.add(BarEntry(point, it.toFloat()))
            point += 5f

            chartList.add(BarEntry(point, it.toFloat()))
            chartList.add(BarEntry(point, 0f))
            point += 5f
        }*/

        //chartList.add(BarEntry(10000f, 0f))

       /* chartList.add(BarEntry(10000f, 0f))
        chartList.add(BarEntry(10000f, 3f))
        chartList.add(BarEntry(19000f, 3f))
        chartList.add(BarEntry(19000f, 0f))

        chartList.add(BarEntry(20000f, 0f))
        chartList.add(BarEntry(20000f, 5f))
        chartList.add(BarEntry(29000f, 5f))
        chartList.add(BarEntry(29000f, 0f))

        chartList.add(BarEntry(30000f, 0f))
        chartList.add(BarEntry(30000f, 4f))
        chartList.add(BarEntry(39000f, 4f))
        chartList.add(BarEntry(39000f, 0f))

        chartList.add(BarEntry(40000f, 0f))
        chartList.add(BarEntry(40000f, 7f))
        chartList.add(BarEntry(49000f, 7f))
        chartList.add(BarEntry(49000f, 0f))

        chartList.add(BarEntry(50000f, 0f))
        chartList.add(BarEntry(50000f, 5f))
        chartList.add(BarEntry(59000f, 5f))
        chartList.add(BarEntry(59000f, 0f))

        chartList.add(BarEntry(60000f, 0f))
        chartList.add(BarEntry(60000f, 10f))
        chartList.add(BarEntry(69000f, 10f))
        chartList.add(BarEntry(69000f, 0f))

        chartList.add(BarEntry(70000f, 0f))
        chartList.add(BarEntry(70000f, 4f))
        chartList.add(BarEntry(79000f, 4f))
        chartList.add(BarEntry(79000f, 0f))*/

        for(i in 1..20){
            val end = i * 50000f - 3000f
            val start = end - 44000f

            val count = (1..30).random().toFloat()

            chartList.add(BarEntry(start, 0f))
            chartList.add(BarEntry(start, count))
            chartList.add(BarEntry(end, count))
            chartList.add(BarEntry(end, 0f))

        }

        rangeBarWithChart.setEntries(chartList)


       /* val array = arrayOf(
           0.0f to 5.0f, 1.0f to 5.0f, 1.0f to 0.0f, 2.0f to 0.0f, 2.0f to 5.0f, 3.0f to 5.0f
        ).map { BarEntry(it.first, it.second) }
        // 위 같이 데이터 넣으면 왜 오류????

        val arrList = ArrayList<BarEntry>()
        arrList.addAll(array)

        rangeBarWithChart.setEntries(arrList)*/

        rangeBarWithChart.onLeftPinChanged = { index, leftPinValue ->
            Log.d("AppTest", "leftpin / index : ${index}, value : ${leftPinValue}")
            etLeftPin.setText(leftPinValue.toString())
        }
        rangeBarWithChart.onRightPinChanged = { index, rightPinValue ->
            Log.d("AppTest", "rightpin / index : ${index}, value : ${rightPinValue}")
            etRightPin.setText(rightPinValue.toString())
        }



    }


}