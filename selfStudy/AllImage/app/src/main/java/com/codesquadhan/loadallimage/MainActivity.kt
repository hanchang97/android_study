package com.codesquadhan.loadallimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fileList = ArrayList<String>()
        var uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var projection = arrayOf(MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME)

        var cursor = contentResolver.query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc")

        var columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        var columnDisplayName = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
        var lastIndex = 0

        while(cursor!!.moveToNext()){
            Log.d("AppTest", "cursor")
            var absolutePathOfImage = cursor.getString(columnIndex!!)
            var nameOfFile = cursor.getString(columnDisplayName!!)

            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile)
            if(lastIndex >= 0)
            else lastIndex = nameOfFile.length - 1

            if(!TextUtils.isEmpty(absolutePathOfImage)){
                fileList.add(absolutePathOfImage)
                Log.d("AppTest", "isEmpty")
            }
        }


        Log.d("AppTest", "image List : ${fileList}")

    }
}


// 매니페스트에 권한 설정 후 앱 설정가서 권한을 허용해야 제대로 uri를 읽어온다!!! -> MainActivity, SecondAcitivy 둘 모두!!!