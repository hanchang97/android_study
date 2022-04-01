package com.codesquadhan.coroutinestudy.data.repository

import android.app.DownloadManager
import android.util.Log
import com.codesquadhan.coroutinestudy.common.RetrofitBuilder
import com.codesquadhan.coroutinestudy.data.api.SearchImageService
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn

class ImageSearchRepository {
    private val retrofitBuilder = RetrofitBuilder
    private val service : SearchImageService = retrofitBuilder.retrofit.create(SearchImageService::class.java)

    /*suspend fun getImageSearch(query: String) : Flow<List<ImageSearchResponse>> {
        return service.getImages(query).body()?.asFlow()
    }*/

    suspend fun getImageSearch(query: String): List<ImageSearchResponse>?{
        Log.d("AppTest", "thread repository : ${Thread.currentThread()}")
        return service.getImages(query).body()
    }

}







// flow
// 비동기이며 코루틴에서만 동작 가능한 것은 suspend function 과 동일하다.
// 다른 점은 함수 앞에 suspend 를 붙이지 않아도 된다.
