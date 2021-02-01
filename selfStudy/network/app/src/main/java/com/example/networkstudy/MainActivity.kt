package com.example.networkstudy

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networkstudy.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var activityMainBinding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding = binding
        setContentView(activityMainBinding!!.root)

        NetworkTask(
            activityMainBinding?.recyclerPerson!!,
            LayoutInflater.from(this)
        ).execute()

    }
}

// 네트워크 통신은 메인쓰레드에서 X !!
class NetworkTask(
    val recyclerView : RecyclerView,
    val inflater : LayoutInflater,

):AsyncTask<Any?, Any?, Array<Person>>(){
    override fun doInBackground(vararg params: Any?): Array<Person>? {
        val urlString : String = "http://mellowcode.org/json/students/"   //http 허용하려면 manifest에서 application 부분에 android:usesCleartextTraffic="true" 추가 해야한다
        val url : URL = URL(urlString)
        val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod  = "GET"
        connection.setRequestProperty("Content-Type", "application/json")  //header 작성 / body의 요약을 위한 것

        var buffer = ""
        if(connection.responseCode == HttpURLConnection.HTTP_OK){
            Log.d("connection","inputstream : " + connection.inputStream) // 로그 출력 형태 보기
            val reader = BufferedReader(
                    InputStreamReader(
                            connection.inputStream,
                            "UTF-8"   //UTF-8 형식으로 inputStream 읽겠다
                    )
            )
            buffer = reader.readLine()
            Log.d("connection", "inputstream : " + buffer)  //사람이 이해할 수 있는 형태로 변경
        }

            val data = Gson().fromJson(buffer, Array<Person>::class.java)
            Log.d("connection","data : " + data)

            val age = data[0].age
            val id = data[0].id
            val intro = data[0].intro
            val name = data[0].name

            Log.d("connection","${age} ${id} ${intro} ${name}")

        return data // = Array<Person>
    }

    override fun onPostExecute(result: Array<Person>) {  // 이 부분은 UI Thread에 접근 가능!! -> View 그리는 작업  이곳에서 작성
        val adapter = PersonRecyclerAdapter(result, inflater)
        recyclerView.adapter = adapter

        // xml에서 reclcyerview 에 layoutmanager 속성 설정해주기

        super.onPostExecute(result)
    }
}




