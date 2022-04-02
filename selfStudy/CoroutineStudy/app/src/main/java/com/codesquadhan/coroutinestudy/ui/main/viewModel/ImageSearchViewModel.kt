package com.codesquadhan.coroutinestudy.ui.main.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquadhan.coroutinestudy.data.repository.ImageSearchRepository
import com.codesquadhan.coroutinestudy.model.ImageSearchResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ImageSearchViewModel(private val imageSearchRepository : ImageSearchRepository): ViewModel() {
    //private val imageSearchRepository = ImageSearchRepository()  // 의존성 주입을 하도록 수정하자

    private val queryFlow = MutableSharedFlow<String>()  // 메인액티비티와 뷰모델의 데이터 공유를 위해 SharedFlow를 사용하고 있다

    private val _images = MutableLiveData<List<ImageSearchResponse>>()
    val images: LiveData<List<ImageSearchResponse>> = _images

    /*val imageFlow = queryFlow.flatMapLatest {

    }

    fun sendQuery(query: String) {  // 사용자가 입력한 문자열을 queryFlow에 추가,   MainActivity에서 호출
        viewModelScope.launch {
            queryFlow.emit(query)
        }
    }*/

    fun searchImage(query: String){
        viewModelScope.launch() {
            Log.d("AppTest", "thread viewModel : ${Thread.currentThread()}")
            _images.value = imageSearchRepository.getImageSearch(query)

        }
    }


}