package com.codesquadhan.coroutinestudy.ui.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codesquadhan.coroutinestudy.data.repository.ImageSearchRepository
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ImageSearchViewModel(private val imageSearchRepository : ImageSearchRepository): ViewModel() {
    //private val imageSearchRepository = ImageSearchRepository()  // 의존성 주입을 하도록 수정하자

    private val queryFlow = MutableSharedFlow<String>()  // 메인액티비티와 뷰모델의 데이터 공유를 위해 SharedFlow를 사용하고 있다

    /*
    private val _images = MutableLiveData<List<ImageSearchResponse>>()
    val images: LiveData<List<ImageSearchResponse>> = _images
    */


    val imageFlow = queryFlow.flatMapLatest { // 사용자가 입력한 키워드가 바뀔 수 있으므로  flatMapLatest 를 사용!!! -> 이전 이미지 검색 결과를 날려버린다
        searchImages(it)
    }.cachedIn(viewModelScope)
    // cachedIn 연산자는 데이터 스트림을 공유 가능하게 하며 제공된 CoroutineScope을 사용하여 로드된 데이터를 캐시합니다
    // viewModelScope -> 처리된 결과를 현재 뷰모델의 수명 범위 내에서만 가지고 있겠다는 의미


    fun sendSearchQuery(searchQuery: String) {  // 사용자가 입력한 문자열을 queryFlow에 추가,  버튼 누를 시 추가
        viewModelScope.launch {
            queryFlow.emit(searchQuery)
        }
    }

    private fun searchImages(searchQuery: String?): Flow<PagingData<ImageSearchResponse>> =
        imageSearchRepository.getImageSearch(searchQuery)

}