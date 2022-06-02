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

    override fun getRefreshKey(state: PagingState<Int, ImageSearchResponse>): Int? {  // refresh 하는 경우 다시 시작할 key를 반환
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(defaultDisplay) ?: anchorPage?.nextKey?.minus(defaultDisplay)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageSearchResponse> {
        // params = 실행할 로드 작업에 관한 정보가 포함됩니다. 여기에는 로드할 키와 로드할 항목 수가 포함

        val start = params.key ?: defaultStart  // params에 key 값 있으면 사용 , 없으면 디폴트 사용,   start = 검색 시작 위치

        return try {
            val response = imageSearchService.getImages(searchQuery)  // 검색어를 바탕으로 랜덤 이미지 불러오기
            // 페이지 정보 혹은 아이템 검색 시작 위치를 요청할 수 있는 경우  params.loadSize 혹은 start를 활용할 수 있을 것

            val items = response   // 아이템 리스트
            val nextKey = if (items.isEmpty()) {
                null   // 더 이상 load 할 것 없으면 null
            } else {
                start + params.loadSize   // 1 에서 시작해서 30개를 가져오면  다음 데이터 시작은 31번째 부터,

                //  페이지 번호를 사용하는 api 경우와 약간 다름 -> 이 경우는 start + 1 과 같은 형태?
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

    // 현재 사용한 unsplash 이미지는 페이지 or 아이템 번호 관련 정보가 없어서 추후 위 정보를 요청할 수 있는 api 적용해보기
    // 현재는 스크롤 밑에 도달할 시 랜덤으로 30개의 데이터를 추가 요청하는 방식
}