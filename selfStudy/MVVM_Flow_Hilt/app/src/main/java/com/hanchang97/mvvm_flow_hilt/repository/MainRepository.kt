package com.hanchang97.mvvm_flow_hilt.repository

import com.hanchang97.mvvm_flow_hilt.model.Post
import com.hanchang97.mvvm_flow_hilt.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost(): Flow<List<Post>> = flow {
        emit(apiService.getPost())
    }
}