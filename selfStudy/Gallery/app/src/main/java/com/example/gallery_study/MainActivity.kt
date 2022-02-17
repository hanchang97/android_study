package com.example.gallery_study

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gallery_study.databinding.ActivityMainBinding

const val REQUEST_IMAGE_GET = 100

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setButton1()
        setButton2()
    }

    fun setButton1(){
        binding.btn1.setOnClickListener {
            selectImage1()
        }
    }

    fun setButton2(){  // registerForActivityResult를 버튼의 setOnClickListener 안에서 호출 하면 오류 발생!! 초기화 시점이 중요한 듯
        var getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            Log.d("AppData", "data : ${it.data}")
            binding.iv.setImageURI(it.data?.data)
        }

        binding.btn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK) // 갤러리로 이동
            //val intent = Intent(Intent.ACTION_GET_CONTENT)  // 전체 이미지 관련 파일 선택 가능한 화면으로 이동
            intent.type = "image/*"
            getContent.launch(intent)
        }
    }

    fun selectImage1(){
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK) {
            val thumbnail: Bitmap? = data?.getParcelableExtra("data")
            val fullPhotoUri: Uri? = data?.data

            // Do work with photo saved at fullPhotoUri
            Log.d("AppTest", "photo uri : $fullPhotoUri")
            binding.iv.setImageURI(fullPhotoUri)
        }
    }
}