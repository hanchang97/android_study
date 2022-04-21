package com.example.android.hilt.data

import dagger.hilt.android.scopes.ActivityScoped
import java.util.*
import javax.inject.Inject

@ActivityScoped   // Activity 컨테이너로 지정
class LoggerInMemoryDataSource @Inject constructor() : LoggerDataSource {  // Hilt에게 생성방법 알려주기 위해 Inject 어노테이션 사용

    private val logs = LinkedList<Log>()

    override fun addLog(msg: String) {
        logs.addFirst(Log(msg, System.currentTimeMillis()))
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        callback(logs)
    }

    override fun removeLogs() {
        logs.clear()
    }
}

// 이 방식은 앱 실행중에만 기록 남고, 껐다 다시 키면 사라짐!!!