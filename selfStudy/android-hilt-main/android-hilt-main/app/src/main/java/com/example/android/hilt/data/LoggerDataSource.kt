package com.example.android.hilt.data

interface LoggerDataSource {
    fun addLog(msg: String)
    fun getAllLogs(callback: (List<Log>) -> Unit)
    fun removeLogs()
}


// LoggerLocalDataSource는 ButtonsFragment와 LogsFragment, 두 프래그먼트에서 사용됩니다.
// LoggerDataSource 인스턴스를 사용하기 위해 두 프래그먼트를 사용하도록 리팩터링해야 합니다.