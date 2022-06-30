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
import com.nimok97.backstack.databinding.FragmentSearchBinding

class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PrintLog.printLog("SearchFragment / onCreateView")

        FragmentNumber.FRAGMENT_NUMBER = 2    //

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrintLog.printLog("SearchFragment / onViewCreated")

        // Search2Fragment 로 이동
        binding.btnSearch.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, Search2Fragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        PrintLog.printLog("SearchFragment / onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        PrintLog.printLog("SearchFragment / onDestroy")
    }
}