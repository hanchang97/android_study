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
import com.nimok97.backstack.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PrintLog.printLog("HomeFragment / onCreateView")

        FragmentNumber.FRAGMENT_NUMBER = 1 //

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrintLog.printLog("HomeFragment / onViewCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PrintLog.printLog("HomeFragment / onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        PrintLog.printLog("HomeFragment / onDestroy")
    }
}