package com.nimok97.backstack.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nimok97.backstack.R
import com.nimok97.backstack.common.FragmentNumber
import com.nimok97.backstack.common.PrintLog
import com.nimok97.backstack.databinding.FragmentSearch2Binding

class Search2Fragment: Fragment() {

    private lateinit var binding: FragmentSearch2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PrintLog.printLog("Search2Fragment / onCreateView")

        FragmentNumber.FRAGMENT_NUMBER = 4
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search2, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrintLog.printLog("Search2Fragment / onViewCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PrintLog.printLog("Search2Fragment / onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        PrintLog.printLog("Search2Fragment / onDestroy")
    }
}