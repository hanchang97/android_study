package com.codesquadhan.coroutinestudy.ui.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codesquadhan.coroutinestudy.data.repository.ImageSearchRepository

class ImageSearchViewModelFactory(
    private val repository: ImageSearchRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageSearchViewModel(repository) as T
    }
}





// 참고링크
// https://codechacha.com/ko/android-jetpack-create-viewmodel/
//