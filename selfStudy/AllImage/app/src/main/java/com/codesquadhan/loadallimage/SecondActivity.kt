package com.codesquadhan.loadallimage

import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null
        )

        var uriArr = ArrayList<String>()

        if(cursor != null){
            Log.d("AppTest", "check")
            while (cursor.moveToNext()){
                var uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                uriArr.add(uri)
            }
            Log.d("AppTest", "uriArr : $uriArr")
            cursor.close()
        }else{
            Log.d("AppTest", "cursor null")
        }
    }


}