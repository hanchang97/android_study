package com.example.gallery_study

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gallery_study.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


const val REQUEST_IMAGE_GET = 100

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setButton1()
        setButton2()
        setButton3() // 앱 선택기 직접
        setButton4() // 권한 요청
    }

    fun setButton1() {
        binding.btn1.setOnClickListener {
            selectImage1()
        }
    }

    fun setButton2() {  // registerForActivityResult를 버튼의 setOnClickListener 안에서 호출 하면 오류 발생!! 초기화 시점이 중요한 듯
        var getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == RESULT_OK) {
                    Log.d("AppTest", "data : ${it.data}")
                    binding.iv.setImageURI(it.data?.data)
                } else {
                    Log.d("AppTest", "btn2 사진 선택 x")
                }
            }

        binding.btn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK) // 갤러리로 이동
            //val intent = Intent(Intent.ACTION_GET_CONTENT)  // 전체 이미지 관련 파일 선택 가능한 화면으로 이동
            intent.type = "image/*"

            val chooser: Intent = Intent.createChooser(intent, "Chooser Test")

            //getContent.launch(intent)
            getContent.launch(chooser)
        }
    }

    fun selectImage1() {
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
        } else {
            Log.d("AppTest", "사진 선택 x")
        }
    }

    fun setButton3() {
        //val sendIntent = Intent(Intent.ACTION_SEND)
        //val sendIntent = Intent(Intent.ACTION_GET_CONTENT)
        val sendIntent = Intent(Intent.ACTION_PICK)
        sendIntent.type = "image/*"

        //val title: String = resources.getString(R.string.chooser_title)
        val chooser: Intent = Intent.createChooser(sendIntent, "CHOOSER TEST")

        binding.btn3.setOnClickListener {
            if (sendIntent.resolveActivity(packageManager) != null) {
                //startActivity(chooser)
                startActivityForResult(chooser, REQUEST_IMAGE_GET)
            } else {
                Log.d("AppTest", "nothing to execute")
            }
        }
    }

    fun setButton4() {
        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()
            ) {
                if (it.resultCode == RESULT_OK) {
                    Log.d("AppTest", "data : ${it.data}")
                    binding.iv.setImageURI(it.data?.data)
                } else {
                    Log.d("AppTest", "btn4 사진 선택 x")
                }
            }

        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Log.d("AppTest", "권한 승인 ok")
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    getContent.launch(Intent.createChooser(intent, "Chooser Test"))
                } else {
                    Snackbar.make(binding.root, "권한이 승인되지 않았습니다", Snackbar.LENGTH_SHORT ).show()
                }
            }

        binding.btn4.setOnClickListener {
            requestPermissionLauncher.launch(
                Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("AppTest", "$this - onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("AppTest", "$this - onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AppTest", "$this - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AppTest", "$this - onStop")
    }
}