package com.example.catureblockandopenpdf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.catureblockandopenpdf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE) // 현재 액티비티 캡쳐 방지하기

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // webview 로 pdf 열기
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.setSupportZoom(true) // 확대 기능 허용
        binding.webView.settings.javaScriptEnabled = true

        val url = "https://www.clickdimensions.com/links/TestPDFfile.pdf"   // 테스트용 pdf 링크
        binding.webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")

        // 로드 시작~ 로드 완료 사이 progressbar 구현을 위해 webView 이벤트 컨트롤 해보기
    }
}

private class WebViewClientCustom : WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }
}