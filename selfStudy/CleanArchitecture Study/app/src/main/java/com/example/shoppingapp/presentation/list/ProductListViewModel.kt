package com.example.shoppingapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.GetProductListUseCase
import com.example.shoppingapp.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class ProductListViewModel(
        private val getProductListUseCase: GetProductListUseCase
): BaseViewModel() {

    // 통신 상태를 위한 라이브 데이터
    private var _productListStateLiveData = MutableLiveData<ProductListState>(ProductListState.Uninitialized)
    val productListStateLiveData: LiveData<ProductListState> = _productListStateLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        setState(
                ProductListState.Loading
        )

        setState(
                ProductListState.Success(
                        getProductListUseCase()
                )
        )
    }

    private fun setState(state: ProductListState){
        _productListStateLiveData.postValue(state)
    }
}