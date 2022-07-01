package com.nimok97.backstack.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nimok97.backstack.common.Common
import com.nimok97.backstack.common.PrintLog
import com.nimok97.backstack2.R
import com.nimok97.backstack2.common.FragmentName
import com.nimok97.backstack2.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PrintLog.printLog("HomeFragment / onCreateView")

        Common.setCurrentFragment(FragmentName.HOME) //

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