package com.hanchang97.mvvm_flow_hilt.repository

import com.hanchang97.mvvm_flow_hilt.model.Post
import com.hanchang97.mvvm_flow_hilt.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost(): Flow<List<Post>> = flow {
        //kotlinx.coroutines.delay(6000L)
        log("MainRepository, flow 시작")
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)

    // flowOn 에서 Dispatcher를 IO로 지정하지 않아도 동작은 한다
    // 1. 데이터 양이 많지 않고 로드 속도가 빨라서 괜찮은 것?
    // 2. retrofit에서 IO 스레드 처리?


    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
}