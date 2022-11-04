package com.nimok97.workmanagertimer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class TimerWorker(private val appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private val currentTimeStamp = inputData.getLong("timeStamp", 0L)

    override suspend fun doWork(): Result {
        return try {
            Log.e("AppTest", "TimerWorker : current time stamp : $currentTimeStamp")

            Log.e("AppTest", "TimerWorker : workInfo 1 START")
            //delay(10000)

            delay(3000)

            Log.e("AppTest", "TimerWorker : workInfo 1 END")

            Result.success()
        } catch (exception: Exception) {
            Log.e("AppTest", "TimerWorker : workInfo 1 FAIL")
            Result.failure()
        }
    }




}