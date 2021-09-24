package com.example.cmc_rxstudy

import android.annotation.SuppressLint
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.cmc_rxstudy.databinding.ActivityMainBinding
import com.example.cmc_rxstudy.model.repository.GitRepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    // 깃허브 api -> 사용자 repo 가져와보기
    // delay 조금 줘서  로딩표시 보이게 해주기
    // 아이디 검색 -> 감지
    // 받아온 데이터를 리사이클러뷰에 업데이트하기

    private lateinit var binding: ActivityMainBinding
    private val repoRepository = GitRepoRepository()

    private var progressSubject = PublishSubject.create<Boolean>()
    // Subject 클래스는 Observable의 속성과 구독자의 속성이 모두 있다

    private var resultData = PublishSubject.create<String>()

    private val disposables: CompositeDisposable = CompositeDisposable() // oncomplete 호출 되면 자동 dispose? -> 추가학습하기


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.btnSearch.setOnClickListener {
            val owner = binding.etGithubId.text.toString() // edittext 에 적은 내용
            getRepository(owner)
        }


        // 통신 시 나타나는 progressbar
        progressSubject.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                if(it){
                    binding.progressbar.visibility = View.VISIBLE
                }
                else{
                    binding.progressbar.visibility = View.GONE
                }
            }

        // 통신 결과 갱신
        resultData.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                binding.tvResult.text = it
            }

    }

    @SuppressLint("CheckResult")
    fun getRepository(owner: String){
       progressSubject.onNext(true)
        repoRepository.getRepo(owner)
            .delay(700L, TimeUnit.MILLISECONDS)
            .subscribe(
                {
                   //binding.progressbar.visibility = View.GONE
                    // 여기서 UI 요소에 접근하면 오류! - ui thread가 아니기 때문
                    progressSubject.onNext(false)
                    it.forEach {
                        Log.d("api result", "result + " + it)
                    }
                    // 서버로 부터 받은 데이터 활용
                    resultData.onNext(it.toString())

                },{
                    //binding.progressbar.visibility = View.GONE
                    progressSubject.onNext(false)
                    Log.d("api result", "msg + " + it)
                    // 에러 처리
                    resultData.onNext("올바른 id를 입력해주세요")
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }


}