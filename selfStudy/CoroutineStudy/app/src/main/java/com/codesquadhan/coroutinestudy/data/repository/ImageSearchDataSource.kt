package com.codesquadhan.coroutinestudy.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codesquadhan.coroutinestudy.data.api.SearchImageService
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse
import java.lang.Exception

class ImageSearchDataSource(
    private val searchQuery: String?,
    private val imageSearchService: SearchImageService
): PagingSource<Int, ImageSearchResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ImageSearchResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageSearchResponse> {
        val start = params.key ?: defaultStart  // params에 key 값 있으면 사용 , 없으면 디폴트 사용

        return try {
            // 여러 개 해야 한다면  async
            val response = imageSearchService.getImages(searchQuery)  // 호출 후 잠들기, page가 있는 api의 경우만 가능??

            val items = response
            val nextKey = if (items.isEmpty()) {
                null   // 더 이상 load 할 것 없으면 null
            } else {
                start + params.loadSize
            }
            val prevKey = if (start == defaultStart) {
                null
            } else {
                start - defaultDisplay
            }
            LoadResult.Page(items, prevKey, nextKey)  // LoadResult 객체에는 로드 작업의 결과가 포함된다, 로드 성공 시 LoadResult.Page 객체 리턴
        } catch (exception: Exception) {
            LoadResult.Error(exception)  // 로드 실패 시 LoadResult.Error 객체 반환
        }
    }



    companion object {
        const val defaultStart = 1
        const val defaultDisplay = 30
    }
}