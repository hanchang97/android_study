package com.example.cmc_rxstudy.model.data

import com.google.gson.annotations.SerializedName

data class GitRepo(
    @SerializedName("name") val repoName: String,  // 레포지토리 이름
    @SerializedName("html_url") val repo_url: String, // 레포지토리 주소
    @SerializedName("created_at") val created_at: String // 레포지토리 생성일
)
