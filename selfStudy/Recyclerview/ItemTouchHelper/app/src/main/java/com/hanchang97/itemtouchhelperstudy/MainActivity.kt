package com.hanchang97.itemtouchhelperstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.hanchang97.itemtouchhelperstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    private val dataList = ArrayList<MyData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ItemTouchHelper.Callback 을 리사이클러뷰와 연결
        val swipeHelper = SwipeHelper().apply {
            setClamp(200f)
        }
        val itemTouchHelper = ItemTouchHelper(swipeHelper)
        itemTouchHelper.attachToRecyclerView(binding.rvData)

        rvAdapter = RvAdapter{
            Log.d("AppTest", "LongClick")
            binding.btnBack.isVisible = true

            itemTouchHelper.attachToRecyclerView(null)  // 롱 클릭 후 체크박스 모드에서는 스와이프 되지 않게 하기 위함!!
            rvAdapter.makeCheckBosVisible()
        }

        binding.rvData.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

            setOnTouchListener { v, event ->
                swipeHelper.removePreviousClamp(this)
                false
            }
        }

        /// 체크박스 영역 활성화/비활성화 시 번지는? 잔상 효과 없애기 위함
        val animator = binding.rvData.itemAnimator     //리사이클러뷰 애니메이터 get
        if (animator is SimpleItemAnimator){          //아이템 애니메이커 기본 하위클래스
            animator.supportsChangeAnimations = false  //애니메이션 값 false (리사이클러뷰가 화면을 다시 갱신 했을때 뷰들의 깜빡임 방지)
        }
        ////

        addData()
        rvAdapter.submitList(dataList.toList())

        binding.btnBack.setOnClickListener {
            //it.isVisible = false
            itemTouchHelper.attachToRecyclerView(binding.rvData)
            rvAdapter.makeCheckBoxGone()
        }
    }

    private fun addData(){
        for(i in 1..20){
            dataList.add(MyData(i, "RecyclerView item $i", "https://media.istockphoto.com/vectors/sample-sign-sample-square-speech-bubble-sample-vector-id1161352480?k=20&m=1161352480&s=612x612&w=0&h=uVaVErtcluXjUNbOuvGF2_sSib9dZejwh4w8CwJPc48="))
        }
    }


}