package com.codesquadhan.coroutinestudy.data.api

import com.codesquadhan.coroutinestudy.common.Key
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchImageService {

    /*
    @GET(
        "photos/random?" +
                "client_id=${Key.UNSPLASH_ACCESS_KEY}" +
                "&count=30"
    )
    suspend fun getImages(
        @Query("query") query: String?
    ) : Response<List<ImageSearchResponse>>
    */


    @GET(
        "photos/random?" +
                "client_id=${Key.UNSPLASH_ACCESS_KEY}" +
                "&count=30"
    )
    suspend fun getImages(
        @Query("query") query: String?
    ): List<ImageSearchResponse>
}


// 디폴트 사이즈 30