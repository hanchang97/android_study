package com.hanchang97.mvvm_flow_hilt.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanchang97.mvvm_flow_hilt.model.Post
import com.hanchang97.mvvm_flow_hilt.repository.MainRepository
import com.hanchang97.mvvm_flow_hilt.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _postStateFlow: MutableStateFlow<ApiState<List<Post>>> =
        MutableStateFlow(ApiState.Empty)
    val postStateFlow: StateFlow<ApiState<List<Post>>> = _postStateFlow

    private val ceh = CoroutineExceptionHandler { _, exception ->
        Log.d("AppTest","Exception!: $exception")
    } // catch에서 예외를 처리해서 ceh 로는 가지 않는 것 같음

    fun getPost() = viewModelScope.launch(ceh) {
        _postStateFlow.value = ApiState.Loading // 로딩 프로그레스바 활성화
        mainRepository.getPost()
            .catch { e ->
                _postStateFlow.value = ApiState.Error(e.message)
            }.collect { data ->
                _postStateFlow.value = ApiState.Success(data)
                log("MainViewModel, flow success")
            }
    }

    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
}