package com.nimok97.backstack2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.nimok97.backstack.common.Common
import com.nimok97.backstack.common.PrintLog
import com.nimok97.backstack.fragment.HomeFragment
import com.nimok97.backstack.fragment.MyPageFragment
import com.nimok97.backstack.fragment.SearchFragment
import com.nimok97.backstack2.common.FragmentName
import com.nimok97.backstack2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment by lazy { HomeFragment() }
    private val searchFragment by lazy { SearchFragment() }
    private val myPageFragment by lazy { MyPageFragment() }
    // 프래그먼트 최초 사용 시 초기화하도록 by lazy 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setBottomNavigaionBar()
        changeFragment(homeFragment) // 시작은 HomeFragment
    }

    private fun setBottomNavigaionBar() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.fragment_home -> {
                        clearBackStack()
                        changeFragment(homeFragment)

                        // clearBackStack을 changeFragment 밑에 배치하면
                        // Search2 에서 Home탭 으로 이동 후 Search 프래그먼트로 이동된다
                        // -> 백스택이 제거되면 이전의 Search가 있기 때문!
                    }
                    R.id.fragment_search -> {
                        changeFragment(searchFragment)
                    }
                    R.id.fragment_mypage -> {
                        changeFragment(myPageFragment)
                    }
                }
                true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    private fun clearBackStack() {
        PrintLog.printLog("clear back stack")
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

//        val backStackEntry = supportFragmentManager.backStackEntryCount
//        if (backStackEntry > 0) {
//            for(i in 0 until backStackEntry){
//                supportFragmentManager.popBackStackImmediate()
//            }
//        }
    }

    override fun onBackPressed() {
        if (Common.getCurrentFragment() == FragmentName.HOME) {
            super.onBackPressed()
        } else if (Common.getCurrentFragment() == FragmentName.SEARCH || Common.getCurrentFragment() == FragmentName.MYPAGE) {
            binding.bottomNavigationView.selectedItemId = R.id.fragment_home
        } else {
            super.onBackPressed()
        }
    }
    // 전역변수 FRAGMENT_NUMBER 값에 따라 뒤로가기 동작 처리
}