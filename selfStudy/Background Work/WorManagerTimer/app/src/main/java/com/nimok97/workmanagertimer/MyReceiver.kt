package com.nimok97.workmanagertimer

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var currentTime = 0L
        intent?.let {
            val intentTest = it.getLongExtra("currentTime", 0L)
            Log.e("AppTest", "intent test : $intentTest")
            currentTime = intentTest
        }

        createNotificationChannel(context!!)
        deliverNotification(context)

        val workManager = WorkManager.getInstance(context)

        val workRequest1 = OneTimeWorkRequestBuilder<TimerWorker>()
            //.setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .addTag("Timer1")
            .setInputData(getWorkerData(currentTime))
            .build()

        workManager.enqueue(workRequest1)
    }

    private fun getWorkerData(timeStamp: Long): Data {
        val builder = Data.Builder().apply {
            putLong("timeStamp", timeStamp )
        }
        return builder.build()
    }

    private fun createNotificationChannel(context: Context) {
        // 안드로이드 오레오 버전 부터는 알림을 채널에 할당해야 한다
        // 이전 버전에서는 위와 같이 작성하면 크래시 발생

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Test Channel"
            val descriptionText = "This is Test Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            NotificationManagerCompat.from(context).createNotificationChannel(channel)
        }

    }

    private fun deliverNotification(context: Context) {
        val NOTIFICATION_ID = 1000

        val contentIntent = Intent(context, SecondActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID, // requestCode
            contentIntent, // 알림 클릭 시 이동할 인텐트
            PendingIntent.FLAG_MUTABLE
            /*
            1. FLAG_UPDATE_CURRENT : 현재 PendingIntent를 유지하고, 대신 인텐트의 extra data는 새로 전달된 Intent로 교체
            2. FLAG_CANCEL_CURRENT : 현재 인텐트가 이미 등록되어있다면 삭제, 다시 등록
            3. FLAG_NO_CREATE : 이미 등록된 인텐트가 있다면, null
            4. FLAG_ONE_SHOT : 한번 사용되면, 그 다음에 다시 사용하지 않음
             */
        )

        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // 아이콘
            .setContentTitle("Test Title") // 제목
            .setContentText("Test Content") // 내용
            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)


        Log.e("AppTest", "alarm notify")
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }
}