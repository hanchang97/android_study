package com.codesquad_han.observer

interface StatusObserver {
    fun onAbnormalStatus(status: Status)
}