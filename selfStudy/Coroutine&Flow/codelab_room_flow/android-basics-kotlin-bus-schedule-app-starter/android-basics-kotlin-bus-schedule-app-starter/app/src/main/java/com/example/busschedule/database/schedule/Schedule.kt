package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)

// SQL 기본사항 과정에서 알아봤듯이 데이터 테이블에는 각 행을 고유하게 식별하는 기본 키가 있어야 합니다