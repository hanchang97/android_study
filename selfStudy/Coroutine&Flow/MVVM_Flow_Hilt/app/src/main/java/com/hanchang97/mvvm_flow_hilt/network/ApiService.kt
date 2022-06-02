package com.hanchang97.mvvm_flow_hilt.network

import com.hanchang97.mvvm_flow_hilt.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPost(): List<Post>
}