package com.example.bootpaytest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bootpaytest.databinding.ActivityMainBinding
import kr.co.bootpay.Bootpay
import kr.co.bootpay.BootpayAnalytics
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG
import kr.co.bootpay.enums.UX
import kr.co.bootpay.model.BootExtra
import kr.co.bootpay.model.BootUser

class MainActivity : AppCompatActivity() {

    private val stuck = 10

    private lateinit var binding : ActivityMainBinding

    val application_id = "61695dbd7b5ba4002152c815"   // 부트페이 안드로이드 어플리케이션 id  // 사이트에서 자신의 id 직접 확인하기!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BootpayAnalytics.init(this, application_id)

        binding.btnBootpay.setOnClickListener {
            goBootpayRequest()
        }

    }

    fun goBootpayRequest() {
        val bootUser = BootUser().setPhone("010-1234-5678")  // 구매자 전화번호 기입
       // val bootExtra = BootExtra().setQuotas(intArrayOf(0, 2, 3)).setPopup(1)  // 무슨 기능???  // 팝업으로 결제 시 활용하는듯?
        val bootExtra = BootExtra().setPopup(1)

        val stuck = 1 //재고 있음

        Bootpay.init(this)
            .setApplicationId(application_id) // 해당 프로젝트(안드로이드)의 application id 값
            .setContext(this)
            .setBootUser(bootUser)
            .setBootExtra(bootExtra)
            .setUX(UX.PG_DIALOG)
            .setPG(PG.INICIS)  //PG 설정 / 여기서는 KG INICIS 사용
            .setMethod(Method.KAKAO)  // 결제 방식
            .setMethod(Method.BANK)
            .setMethod(Method.CARD)
//                .setUserPhone("010-1234-5678") // 구매자 전화번호
            .setName("니모의 뚝딱") // 결제할 상품명
            .setOrderId("1234") // 결제 고유번호expire_month
            .setPrice(1000) // 결제할 금액 입력
            .addItem("마우's 스", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
            .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
            .onConfirm { message ->
                if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                else Bootpay.removePaymentWindow(); // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                Log.d("confirm", message);
            }
            .onDone { message ->
                Log.d("done", message)
                // PG에서 결제 승인 이후 호출
            }
            .onReady { message ->
                Log.d("ready", message)
            }
            .onCancel { message ->
                Log.d("cancel", message)
            }
            .onError{ message ->
                Log.d("error", message)
            }
            .onClose { message ->
                Log.d("close", "close")
            }
            .request();
    }


}