package com.codesquadhan.network2.model

import android.util.Log
import com.codesquadhan.network2.Util
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MyRepository : BaseRepository{

    override suspend fun getImageData(): Array<ImageData>? {  // 통신 부분을 withContext로 감싸지 않으면 -> android.os.NetworkOnMainThreadException 발생!!!
        return withContext(Dispatchers.IO){
            val urlString : String = Util.URL
            val url : URL = URL(urlString)
            val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

            var data : Array<ImageData>?

            connection.requestMethod  = "GET"
            connection.setRequestProperty("Content-Type", "application/json")  //header 작성 / body의 요약을 위한 것

            var buffer = ""
            if(connection.responseCode == HttpURLConnection.HTTP_OK){
                Log.d("AppTest","http/ inputstream : " + connection.inputStream) // 로그 출력 형태 보기
                val reader = BufferedReader(
                    InputStreamReader(
                        connection.inputStream,
                        "UTF-8"   //UTF-8 형식으로 inputStream 읽겠다
                    )
                )
                buffer = reader.readLine()
                Log.d("AppTest", "http/ inputstream : " + buffer)  //사람이 이해할 수 있는 형태로 변경

                data = Gson().fromJson(buffer, Array<ImageData>::class.java)
                Log.d("AppTest","data : " + data)

            } else{
                data = null
                Log.d("AppTest", "http fail")
            }

            data
        }
    }
}