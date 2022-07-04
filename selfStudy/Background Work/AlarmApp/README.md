## Alarm App

- AlarmManager 사용
- code 일부
```kotlin
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
                    AlarmManager.RTC_WAKEUP, // 다른 방식도 알아보자
                    calendarAlarm.timeInMillis, // calendarAlarm = 알람 설정 시간
                    AlarmManager.INTERVAL_DAY, // 하루 한 번
                    pendingIntent
                )
```

<br>

- setInexactRepeating : 완벽히 정시를 맞추진 않는다, 배터리 절약 효과, 도즈 모드 시 동작x
- setExact : 반복은 안하지만, 더 정확한 시간 가능, 도즈 모드 시 동작x
<br>

- setAndAllowWhileIdle, setExactAndAllowWhileIdle : 도즈 모드에서도 동작-> 이 경우는 중요한 알람 사용 시


---
<br>

### 참고자료
- https://codechacha.com/ko/android-alarmmanager/
- https://aroundck.tistory.com/6032
- https://superwony.tistory.com/99

<br>

