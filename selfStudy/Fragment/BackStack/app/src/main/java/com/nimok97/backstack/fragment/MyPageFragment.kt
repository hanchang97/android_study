package com.nimok97.backstack.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nimok97.backstack.common.PrintLog
import com.nimok97.backstack.R
import com.nimok97.backstack.common.FragmentNumber
import com.nimok97.backstack.databinding.FragmentMypageBinding

class MyPageFragment: Fragment() {

    private lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PrintLog.printLog("MyPageFragment / onCreateView")

        FragmentNumber.FRAGMENT_NUMBER = 3 //

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrintLog.printLog("MyPageFragment / onViewCreated")

        binding.btnMyPage.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MyPage2Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PrintLog.printLog("MyPageFragment / onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        PrintLog.printLog("MyPageFragment / onDestroy")
    }

}