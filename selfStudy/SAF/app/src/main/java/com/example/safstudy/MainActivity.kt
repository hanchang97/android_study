package com.example.safstudy

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Base64
import android.util.Log
import com.example.safstudy.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    val READ_REQUEST_CODE_IAMGE = 2
    val READ_REQUEST_CODE_PDF: Int = 3

    var encodedPdf = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {    // 1
            addCategory(Intent.CATEGORY_OPENABLE)   // 2
            //type = "image/*"    // 3
            type = "application/pdf"   // pdf 선택하는 경우!!
        }

        //startActivityForResult(intent, READ_REQUEST_CODE)   // 4

        binding.btnSaf.setOnClickListener {
            startActivityForResult(intent, READ_REQUEST_CODE_PDF)   // 4
        }



    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if(requestCode == READ_REQUEST_CODE_PDF && resultCode == Activity.RESULT_OK){

            resultData?.data?.also{ uri ->
                Log.e("AppTest", "Uri : ${uri}")
                Log.e("AppTest", "Uri path : ${uri.path}")

                // 파일 업로드 통신하기
                // uri를 파일형태로 만들기?!

               getFileMetaData(uri)

                try{
                    var inputStream = contentResolver.openInputStream(uri)
                    var pdfInbyte = ByteArray(inputStream!!.available())
                    inputStream.read(pdfInbyte)

                    encodedPdf = Base64.encodeToString(pdfInbyte, Base64.DEFAULT)
                    Log.e("AppTest", "encodedpdf : ${encodedPdf}")


                }
                catch (e : IOException){
                    e.printStackTrace()
                }


            }

        }
    }


    @SuppressLint("Range")
    fun getFileMetaData(uri: Uri){
        val cursor: Cursor? = contentResolver.query( uri, null, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayName: String =
                        it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))  // 1
                Log.e("AppTest", "Display Name: $displayName")  // 파일명!!

                val sizeIndex: Int = it.getColumnIndex(OpenableColumns.SIZE)  // 2
                val size: String = if (!it.isNull(sizeIndex)) {
                    it.getString(sizeIndex)
                } else {
                    "Unknown"
                }
                Log.e("AppTest", "Size: $size")   // size = byte 단위!
            }
        }
    }


}