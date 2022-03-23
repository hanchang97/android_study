package com.codesquadhan.network2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquadhan.network2.model.BaseRepository
import com.codesquadhan.network2.model.ImageData
import com.codesquadhan.network2.model.MyRepository
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val repository = MyRepository()

    private val _imageDataList = MutableLiveData<Array<ImageData>>()
    val imageDataList : LiveData<Array<ImageData>> = _imageDataList

    fun getImageData(){
        viewModelScope.launch {
            val resultList = repository.getImageData()

            if(resultList != null){
                _imageDataList.postValue(resultList!!)
            }
            else{
                Log.d("AppTest", "fail")
            }
        }
    }

    // Dispatchers.Main -> suspend 함수를 호출하고 Android UI 프레임워크 작업을 실행하며 LiveData 객체를 업데이트합니다.
}