package com.example.cmc_rxstudy.model.api

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiProvider {
    private const val base_url = "https://api.github.com/"

    private val getRepositoryApi = Retrofit.Builder()
        .baseUrl(base_url)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())    // RxJava 사용
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .connectionPool(ConnectionPool(5,20,TimeUnit.SECONDS))
            .build())
        .build()

    fun getApiClient() = getRepositoryApi
}
