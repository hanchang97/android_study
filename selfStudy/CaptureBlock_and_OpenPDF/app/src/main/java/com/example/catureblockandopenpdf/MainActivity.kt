package com.example.catureblockandopenpdf

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.catureblockandopenpdf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //val url = "https://www.clickdimensions.com/links/TestPDFfile.pdf"   // 테스트용 pdf 링크
    val url = "https://tuktalk.s3.ap-northeast-2.amazonaws.com/a1fd492d-429b-4184-bc8a-7406139a3f9f.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("lifecycle","onCreate called")

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE) // 현재 액티비티 캡쳐 방지하기

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // webview 로 pdf 열기
        //binding.webView.webViewClient = WebViewClient()
        binding.webView.webViewClient = WebViewClientCustom()
        binding.webView.settings.setSupportZoom(true) // 확대 기능 허용
        binding.webView.settings.javaScriptEnabled = true


        binding.webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")

        // 로드 시작~ 로드 완료 사이 progressbar 구현을 위해 webView 이벤트 컨트롤 해보기
    }

    inner class WebViewClientCustom : WebViewClient(){

        val url = "https://www.clickdimensions.com/links/TestPDFfile.pdf"   // 테스트용 pdf 링크

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            // 다른 url 요청 시 아무 일어나지 않도록 true 리턴
            // view!!.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
            // 위 주석 해제 시 어떤 url 와도 위 주석 부분만 실행
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {  //웹뷰 페이지 로딩 시작
            super.onPageStarted(view, url, favicon)
            Toast.makeText(this@MainActivity, "load start" , Toast.LENGTH_SHORT).show()
        }

        override fun onPageFinished(view: WebView?, url: String?) { // 웹뷰 페이지 로딩 완료
            super.onPageFinished(view, url)
            Toast.makeText(this@MainActivity, "load end" , Toast.LENGTH_SHORT).show()
        }
    }


    override fun onResume() {
        Log.d("lifecycle","onResume called")
        super.onResume()
    }

    override fun onPause() {
        Log.d("lifecycle","onPause called")
        super.onPause()
    }

    override fun onStop() {
        Log.d("lifecycle","onStop called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("lifecycle","onDestory called")
        super.onDestroy()
    }
}

