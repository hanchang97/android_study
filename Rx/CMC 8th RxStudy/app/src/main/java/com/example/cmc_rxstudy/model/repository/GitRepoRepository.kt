package com.example.cmc_rxstudy.model.repository

import com.example.cmc_rxstudy.model.api.ApiProvider
import com.example.cmc_rxstudy.model.api.GitRepositoyApi
import com.example.cmc_rxstudy.model.data.GitRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.create

class GitRepoRepository {
    private val api = ApiProvider.getApiClient().create(GitRepositoyApi::class.java)

    fun getRepo(userId : String) : Single<ArrayList<GitRepo>> = api
        .getRepos(userId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}