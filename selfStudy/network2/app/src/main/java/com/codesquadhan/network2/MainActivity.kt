package com.codesquadhan.network2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private lateinit var btnHttp: Button
    private lateinit var ivImage: ImageView

    val viewModel : MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHttp = findViewById(R.id.btnHttp)
        ivImage = findViewById(R.id.ivImage)

        viewModel.imageDataList.observe(this) {
            Log.d("AppTest", "MainActivity/ $it, ${it[0].date}, ${it[0].image}, ${it[0].title}")

            Glide.with(this).load(it[0].image).error(R.drawable.ic_launcher_foreground).into(ivImage)
        }

        btnHttp.setOnClickListener {
            viewModel.getImageData()
        }

    }
}

/*
*  mvvm + 코루틴 + HttpURLConnection  사용 예제
* */