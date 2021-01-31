package com.example.networkstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.networkstudy.databinding.ActivityMainBinding
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


        val urlString : String = "http://mellowcode.org/json/students/"
        val url : URL = URL(urlString)
        val connection : HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod  = "GET"
        connection.setRequestProperty("Content-Type", "application/json")  //header 작성 / body의 요약을 위한 것

        var buffer = ""
        if(connection.responseCode == HttpURLConnection.HTTP_OK){
            Log.d("connection","inputstream : " + connection.inputStream)
            val reader = BufferedReader(
                    InputStreamReader(
                            connection.inputStream,
                            "UTF-8"
                    )
            )
            buffer = reader.readLine()
        }

    }
}