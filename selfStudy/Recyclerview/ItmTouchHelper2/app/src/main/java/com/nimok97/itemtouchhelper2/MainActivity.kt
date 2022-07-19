package com.nimok97.itemtouchhelper2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.nimok97.itemtouchhelper2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemTouchHelperListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private val dataList = ArrayList<MyData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val swipeHelper = SwipeHelper(this, ContextCompat.getDrawable(this, R.drawable.ic_delete)!!)
        val itemTouchHelper = ItemTouchHelper(swipeHelper)
        itemTouchHelper.attachToRecyclerView(binding.rvMain)

        rvAdapter = RvAdapter()
        binding.rvMain.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        addData()
        rvAdapter.submitList(dataList.toList())
    }

    override fun itemSwipe(position: Int) {
        // TODO 어댑터에게 position에 해당하는 데이터를 지울것을 알려줘야 한다
        Log.d("AppTest", "swipe position : ${position}")
        dataList.removeAt(position)
        val tempList = ArrayList<MyData>()
        dataList.forEachIndexed { index, myData ->
            tempList.add(myData.copy())
        }
        rvAdapter.submitList(tempList.toList())
    }

    private fun addData(){
        for(i in 1..20){
            dataList.add(MyData(i, "RecyclerView item $i", "https://media.istockphoto.com/vectors/sample-sign-sample-square-speech-bubble-sample-vector-id1161352480?k=20&m=1161352480&s=612x612&w=0&h=uVaVErtcluXjUNbOuvGF2_sSib9dZejwh4w8CwJPc48="))
        }
    }
}