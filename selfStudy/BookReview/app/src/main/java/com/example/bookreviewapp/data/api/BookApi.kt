package com.example.bookreviewapp.data.api

import com.example.bookreviewapp.data.models.BestSellerDto
import com.example.bookreviewapp.data.models.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("/api/bestSeller.api?output=json&categoryId=100")   // 카테고리 아이디 국내도서에 해당하는 100 값으로 우선 고정
    fun getBestSeller(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>


    @GET("/api/search.api?output=json")
    fun searchBookByName(
        @Query("key") apiKey: String,
        @Query("query") keyWord: String
    ): Call<SearchBookDto>   // response
}