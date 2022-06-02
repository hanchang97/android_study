package com.codesquadhan.coroutinestudy.data.repository

import android.app.DownloadManager
import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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


    /*suspend fun getImageSearch(query: String): List<ImageSearchResponse>?{
        Log.d("AppTest", "thread repository : ${Thread.currentThread()}")
        return service.getImages(query).body()
    }*/


   fun getImageSearch(searchQuery: String?) : Flow<PagingData<ImageSearchResponse>> {  // 왜 그냥 fun??
       return Pager(  // 페이저가 스크롤에 따라 데이터 자동으로 가져오고 flow를 만들어 준다
            PagingConfig(
             pageSize = ImageSearchDataSource.defaultDisplay,  // 실제 서버에 요청하는 아이템 개수와 동일해야 한다
            enablePlaceholders = false   // true 라면, 아직 로드되지 않은 item -> null 표시!
            ),
            pagingSourceFactory = {
                ImageSearchDataSource(searchQuery, service)
            }
       ).flow   // flow 형태로
   }

    // Paging 라이브러리는 Flow, LiveData, RxJava의 Flowable 유형과 Observable 유형을 비롯한 여러 스트림 유형을 사용할 수 있도록 지원

    // Pager 객체는 PagingSource 객체에서 load() 메서드를 호출하여 LoadParams 객체를 제공하고 반환되는 LoadResult 객체를 수신합니다.
}







// flow
// 비동기이며 코루틴에서만 동작 가능한 것은 suspend function 과 동일하다.
// 다른 점은 함수 앞에 suspend 를 붙이지 않아도 된다.
