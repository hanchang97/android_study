package com.example.busschedule.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

@Database(entities = arrayOf(Schedule::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}