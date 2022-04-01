package com.codesquadhan.network2

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import java.util.*


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

            Glide.with(this)
                .asBitmap()
                .load(it[0].image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(object : CustomTarget<Bitmap>(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL){
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val newFile = File(
                            cacheDir.path,
                            Random(SystemClock.currentThreadTimeMillis()).nextLong().toString()
                        ).apply {
                            createNewFile()
                        }
                        FileOutputStream(newFile).use {
                            resource.compress(Bitmap.CompressFormat.JPEG, 100, it)
                        }

                        Log.d("AppTest", "newFile : $newFile")


                        // 생성한 newFile을 저장하는 것 해보기!!!!


                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        Log.d("AppTest", "onLoadCleared")
                    }

                })
        }

        btnHttp.setOnClickListener {
            viewModel.getImageData()
        }

    }
}

/*
*  mvvm + 코루틴 + HttpURLConnection  사용 예제
* */