package com.nimok97.workmanagertimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val event = MutableSharedFlow<Boolean>()

    fun emit() {
        viewModelScope.launch{
            event.emit(true)
        }
    }
}