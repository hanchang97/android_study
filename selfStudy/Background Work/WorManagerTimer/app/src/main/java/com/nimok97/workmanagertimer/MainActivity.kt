package com.nimok97.workmanagertimer

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.work.*
import com.nimok97.workmanagertimer.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var workManager: WorkManager
    private lateinit var workRequest1: WorkRequest
    private lateinit var workRequest2: WorkRequest
    private val mainViewModel: MainViewModel by viewModels()

    private var data = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // WorkRequest 만들기
        // Worker 는 작업단위를 정의, WorkRequest는 언제, 어떻게 작업이 실행되어야 하는지 정의

        // Tag!
        // Every work request has a unique identifier,
        // which can be used to identify that work later in order to cancel the work or observe its progress.
        // 예를 들어 WorkManager.cancelAllWorkByTag(String)은 특정 태그가 있는 모든 작업 요청을 취소하고
        // WorkManager.getWorkInfosByTag(String)은 현재 작업 상태를 확인하는 데 사용할 수 있는 WorkInfo 객체 목록을 반환

        setButton()

        workManager = WorkManager.getInstance(application)

        workRequest1 = OneTimeWorkRequestBuilder<TimerWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            //.setInitialDelay(10L, TimeUnit.SECONDS)
            .addTag("Timer1")
            .build()

        // 리퀘스트 2개 만들기5
        // 각각 실행
        // 각각 타이머?
        // 남은시간 텍스트뷰로 보여주기

        //

        workManager.getWorkInfoByIdLiveData(workRequest1.id).observe(this) { workInfo ->
            if (workInfo != null) {
                when (workInfo.state) {
                    WorkInfo.State.RUNNING -> {
                        Log.e("AppTest", "workInfo 1 RUNNING")
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        Log.e("AppTest", "workInfo 1 SUCCEEDED")
                    }
                }
            }
        }

        val workInfo1 = workManager.getWorkInfosByTag("Timer1")
        if (workInfo1.isDone) {
            Log.e("AppTest", "MainActivity : workInfo 1 isDone")
        }

        //////
        // 알람 매니저
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, MyReceiver::class.java)
        intent.putExtra("currentTime", 10000L) // onReceive 로 데이터 전달 가능

        val pendingIntent = PendingIntent.getBroadcast(
            this,
            1,
            intent,
            PendingIntent.FLAG_MUTABLE
        )

        binding.btnRegisterAlarm.setOnClickListener {
            val triggerTime = (SystemClock.elapsedRealtime() + 5 * 1000)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        }
        //

        val alarmManager2 = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent2 = Intent(this, MyReceiver::class.java)
        intent2.putExtra("currentTime", 2000L) // onReceive 로 데이터 전달 가능

        val pendingIntent2 = PendingIntent.getBroadcast(
            this,
            2,
            intent2,
            PendingIntent.FLAG_MUTABLE
        )

        binding.btnRegisterAlarm2.setOnClickListener {
            val triggerTime = (SystemClock.elapsedRealtime() + 5 * 1000)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager2.setExactAndAllowWhileIdle(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent2
                )
            } else {
                alarmManager2.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent2
                )
            }
        }

        /*
            1. ELAPSED_REALTIME : ELAPSED_REALTIME 사용. 절전모드에 있을 때는 알람을 발생시키지 않고 해제되면 발생시킴.
            2. ELAPSED_REALTIME_WAKEUP : ELAPSED_REALTIME 사용. 절전모드일 때도 알람을 발생시킴.
            3. RTC : Real Time Clock 사용. 절전모드일 때는 알람을 발생시키지 않음.
            4. RTC_WAKEUP : Real Time Clock 사용. 절전모드 일 때도 알람을 발생시킴.
             */

        binding.btn3.setOnClickListener {
            mainViewModel.emit()
        }



        binding.btn4.setOnClickListener {

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.event.collect{
                    if(it) {
                        Log.e("AppTest", "emit")
                    }
                }
            }
        }
    }

    private fun setButton() {
        binding.btnFirst.setOnClickListener {
            Log.e("AppTest", "current time : ${System.currentTimeMillis()}")
            workManager.enqueue(workRequest1)
        }
    }
}