package com.nimok97.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nimok97.alarmapp.common.PrintLog
import com.nimok97.alarmapp.databinding.ActivityMainBinding
import java.util.*
import kotlin.time.Duration.Companion.minutes

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var currentHour = 0
    private var currentMinute = 0
    private val calendar = Calendar.getInstance(
        TimeZone.getTimeZone("Asia/Seoul"),
        Locale.KOREA
    ) // 현재 시스템에 설정된 시간 가져오기

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        currentMinute = calendar.get(Calendar.MINUTE)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 알람 시간, 분 데이터
        // 알람 활성화/비활성화 데이터
        // 두 데이터를 sharedpreference에 저장할 것임

        // step0 - 뷰 초기화 (버튼에 클릭 리스너 달기, View에 다른 속성 추가 등)
        initOnOffBtn()
        initChangeAlarmTimeBtn()


        // step1 - 데이터 가져오기
        val model = fetchDataFromSharedPreferences()

        // step2 - View에 데이터 그려주기
        renderView(model)

    }


    private fun initOnOffBtn() {
        binding.btnOnoff.setOnClickListener {

            // 데이터 확인 - tag에 저장했던 model 값 가져온다
            val model = it.tag as? AlarmDisplayModel ?: return@setOnClickListener  // as? = 형변환 실패 시 null
            // return : null 이면 밑의 코드 실행 x 하기 위함
            val newModel = saveAlaramModel(model.hour, model.minute, model.onOff.not())
            renderView(newModel) // 알람 on/off 버튼의 tag 값에 해당하는 model도 갱신

            if(newModel.onOff){
                // 켜진 경우 -> 알람을 등록
                val calendarAlarm = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, newModel.hour)
                    set(Calendar.MINUTE, newModel.minute)

                    if(before(Calendar.getInstance())) {
                        add(Calendar.DATE, 1) // 현재시간보다 이전 시간으로 알람 등록 시 다음 날로 등
                    }
                }

                // 알람매니저 가져오기
                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent(this, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(
                    this,
                    ALARM_REQUEST_CODE,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT // 기존 데이터 있으면 새로운 것으로 업데이트
                )

                // AlarmManager
                alarmManager.setInexactRepeating( // 반복알람,  but 잠자기 모드에서는 울리지 않음!!
                    AlarmManager.RTC_WAKEUP,
                    calendarAlarm.timeInMillis, // calendarAlarm = 알람 설정 시간
                    AlarmManager.INTERVAL_DAY, // 하루 한 번
                    pendingIntent
                )
                //  하루에 한 번씩 펜딩인텐트 실행

                // 잠자기 모드에서 실행  =  alarmManager.setAndAllowWhileIdle() 사용한다!!

            } else {
                // 꺼진 경우 -> 알람을 제거
                cancelAlarm()
            }
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


            PrintLog.printLog("${calendar.get(Calendar.HOUR_OF_DAY)} ${calendar.get(Calendar.MINUTE)}")

            TimePickerDialog(this, { picker, hour, minute ->

                // 람다 = 시간 set 한 경우 동작

                // 시간 가져와서 저장
                val model = saveAlaramModel(hour, minute, false) // 시간 재설정 시 알람은 끄기로 항상 설정됨

                // 해당 데이터로 뷰 업데이트
                renderView(model)

                // 기존 알람 삭제
                cancelAlarm()

            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
                .show()


        }
    }

    private fun saveAlaramModel(
        hour: Int,
        minute: Int,
        onOff: Boolean
    ): AlarmDisplayModel {
        val model = AlarmDisplayModel(
            hour,
            minute,
            onOff
        )

        // 현재 앱만 사용할 것이므로 Mode Private
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(ALARM_KEY, model.makeDataForDB()) // 저장
            putBoolean(ONOFF_KEY, model.onOff) // 켜졌는지 꺼졌는지 저장
            commit() // data 저장을 위함
        }

        return model
    }


    private fun fetchDataFromSharedPreferences(): AlarmDisplayModel {
        val sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)

        val timeDBValue = sharedPreferences.getString(ALARM_KEY, "${currentHour}:${currentMinute}")
            ?: "${currentHour}:${currentMinute}"
        // sharedPreference key 값은 상수로 관리하는 편이 좋음

        val onOffDBValue = sharedPreferences.getBoolean(ONOFF_KEY, false) // 최초 실행 시 알람 꺼져있으므로 false
        val alarmData = timeDBValue.split(":")

        val alarmModel = AlarmDisplayModel(
            alarmData[0].toInt(),
            alarmData[1].toInt(),
            onOffDBValue
        )

        // 알람 등록 여부에 따른 예외처리
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            ALARM_REQUEST_CODE,
            Intent(this, AlarmReceiver::class.java),
            PendingIntent.FLAG_NO_CREATE // FLAG_NO_CREATE = 없으면 안만들고, 있으면 가져온다 / 확인만 하는 용도
        )  // 보낸 펜딩인텐트 있는지 확인

        if(pendingIntent == null && alarmModel.onOff) {
            // 알람은 꺼져 있는, 데이터는 켜져있는 경우
            alarmModel.onOff = false
        } else if (pendingIntent != null && !alarmModel.onOff){
            // 알람 켜져있는, 데이터 꺼져있음
            // 알람 취소
            pendingIntent.cancel()
        }

       return alarmModel
    }

    private fun renderView(model: AlarmDisplayModel){
        binding.tvAmpm.apply {
            text = model.ampmText
        }

        binding.tvTime.apply {
            text = model.timeText
        }

        binding.btnOnoff.apply {
            text = model.onOffText
            tag = model
            // Tags can also be used to store data within a view without resorting to another data structure
        }
    }

    private fun cancelAlarm(){
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            ALARM_REQUEST_CODE,
            Intent(this, AlarmReceiver::class.java),
            PendingIntent.FLAG_NO_CREATE
        ) // getBroadcast 시 실제 알람 등록되어 있지 않으면 null 반환되므로 ? 처리 했음
        pendingIntent?.cancel()
    }

    companion object { // = java static 유사
        private const val SHARED_PREFERENCE_NAME = "time"
        private const val ALARM_KEY = "alarm"
        private const val ONOFF_KEY = "onOff"
        private const val ALARM_REQUEST_CODE = 1000
    }

}


