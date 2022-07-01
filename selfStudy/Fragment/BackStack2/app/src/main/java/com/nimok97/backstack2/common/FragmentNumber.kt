package com.nimok97.backstack.common

import com.nimok97.backstack2.common.FragmentName

object Common {
    private var CURRNET_FRAGMENT = FragmentName.HOME

    fun setCurrentFragment(fragmentName: FragmentName){
        CURRNET_FRAGMENT = fragmentName
    }

    fun getCurrentFragment() = CURRNET_FRAGMENT
}