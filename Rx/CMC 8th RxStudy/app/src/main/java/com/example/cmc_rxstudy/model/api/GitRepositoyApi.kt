package com.example.cmc_rxstudy.model.api

import com.example.cmc_rxstudy.model.data.GitRepo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRepositoyApi {
    // 사용자 id로 레포지토리 조회
    @GET("users/{owner}/repos")
    fun getRepos(@Path("owner") owner: String) : Single<ArrayList<GitRepo>>
}