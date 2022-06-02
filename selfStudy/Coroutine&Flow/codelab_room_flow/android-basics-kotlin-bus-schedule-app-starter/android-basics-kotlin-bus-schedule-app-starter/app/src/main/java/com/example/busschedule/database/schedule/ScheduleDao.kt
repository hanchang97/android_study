package com.example.busschedule.database.schedule

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")  // 모든 데이터, 버스 정류장 도착 시간 오름차순으로 표시하기 위함
    fun getAll(): Flow<List<Schedule>>
    //fun getAll(): List<Schedule>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC") // 선택한 정류장 이름과 일치하는 결과만 원하므로 WHERE 사용 & 도착 시간 오름차순
    fun getByStopName(stopName: String): Flow<List<Schedule>>
    //fun getByStopName(stopName: String): List<Schedule>
}