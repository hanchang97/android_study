package com.example.openpdf2

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.openpdf2.databinding.ActivityMainBinding
import com.example.openpdf2.databinding.ActivityOpenPdfBinding
import java.net.URI

class PdfActivity: AppCompatActivity() {

    private lateinit var binding : ActivityOpenPdfBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityOpenPdfBinding>(this, R.layout.activity_open_pdf)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 제목 보이게하기
        supportActionBar!!.setTitle("pdf 파일 읽기")
        /////////////////////


        // 샘플 pdf 링크
        var sample1 = "https://tuktalk.s3.ap-northeast-2.amazonaws.com/5e191024-d21a-467d-b322-bf878e4c2727.pdf"

        var uri = Uri.parse(sample1)

        //binding.pdfView.fromUri(uri).load()


    }
}