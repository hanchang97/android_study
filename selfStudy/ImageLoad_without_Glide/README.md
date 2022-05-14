## 라이브러리 없이 이미지 url 로드하기

```kotlin
private fun downloadBitmap(imageUrl: String): Bitmap? {
        return try {
            val connection = URL(imageUrl).openConnection()
            connection.connect()

            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            bitmap
        } catch (e: Exception) {
            Log.e("AppTest", "Exception : $e")
            null
        }
    }
```

<br>

- 1  StrictMode
```kotlin
StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitNetwork().build())
val bitmap = downloadBitmap(imgUrl1)
binding.iv1.setImageBitmap(bitmap)
```

[https://noota.tistory.com/entry/StrictMode-간단-정리](https://noota.tistory.com/entry/StrictMode-%EA%B0%84%EB%8B%A8-%EC%A0%95%EB%A6%AC)

<br>

- 2  Handler
```kotlin
val uiHandler = Handler(Looper.getMainLooper())
thread(start = true) {
      Log.d("AppTest", "Current thread name1 : ${Thread.currentThread().name}")
                 
			val bitmap = downloadBitmap(imgUrl1)
      uiHandler.post {
           Log.d("AppTest", "Current thread name2 : ${Thread.currentThread().name}")
                     
					 binding.iv1.setImageBitmap(bitmap)
      }
}
```

<br>

- 3 Coroutine
```kotlin
CoroutineScope(Dispatchers.IO).launch {
                Log.d("AppTest", "Current thread name3 : ${Thread.currentThread().name}")
                val bitmap = downloadBitmap(imgUrl1)

                withContext(Dispatchers.Main) {
                    Log.d("AppTest", "Current thread name4 : ${Thread.currentThread().name}")
                    binding.iv1.setImageBitmap(bitmap)
                }
```

<br>

- 4 Coroutine + HttpURLConnection
```kotlin
  val urlConnection = URL(imgUrl1).openConnection() as HttpURLConnection
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("AppTest", "Current thread name5 : ${Thread.currentThread().name}")
                try {
                    if (urlConnection.responseCode == 200) {
                        val stream = BufferedInputStream(urlConnection.inputStream)
                        val bitmap = BitmapFactory.decodeStream(stream)

                        withContext(Dispatchers.Main){
                            Log.d("AppTest", "Current thread name6 : ${Thread.currentThread().name}")
                            binding.iv1.isVisible = true
                            binding.clImageLoadFail.isVisible = false
                            binding.iv1.setImageBitmap(bitmap)
                        }

                    } else {
                        withContext(Dispatchers.Main){
                            Log.d("AppTest", "Current thread name7 : ${Thread.currentThread().name}")
                            binding.iv1.setImageBitmap(null)
                            binding.iv1.isVisible = false
                            binding.clImageLoadFail.isVisible = true
                        }
                    }
                } catch (e: Exception) {
                    Log.e("AppTest", "Exception : $e")
                    withContext(Dispatchers.Main){
                        Log.d("AppTest", "Current thread name7 : ${Thread.currentThread().name}")
                        binding.iv1.setImageBitmap(null)
                    }
                } finally {
                    urlConnection.disconnect()
                }
            }
```

---

- 메인스레드에서 이미지 로드 시 에러발생
- IO 스레드 사용하기


