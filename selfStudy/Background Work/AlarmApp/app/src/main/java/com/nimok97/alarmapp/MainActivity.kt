package com.nimok97.alarmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nimok97.alarmapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 알람 시간, 분 데이터
        // 알람 활성화/비활성화 데이터
        // 두 데이터를 sharedpreference에 저장할 것임

        // step0 - 뷰 초기화 (버튼에 클릭 리스너 달기, View에 다른 속성 추가 등)
        initOnOffBtn()
        initChangeAlarmTimeBtn()

        // step1 - 데이터 가져오기
        // step2 - View에 데이터 그려주기



    }


    private fun initOnOffBtn() {
        binding.btnOnoff.setOnClickListener {
            // 데이터 확인
            // 알람 on/off 상태 체크

            // off 라면 -> 다시 알람 등록하기
            // on -> 알람 끄기

            // 데이터 저장

        }
    }

    private fun initChangeAlarmTimeBtn() {
        binding.btnChangeAlarmTime.setOnClickListener {
            // 현재시간 가져오기
            // TimePicker 사용하기

            // 시간 가져와서 저장
            // 해당 데이터로 뷰 업데이트
            // 기존 알람 삭제

        }
    }

}


