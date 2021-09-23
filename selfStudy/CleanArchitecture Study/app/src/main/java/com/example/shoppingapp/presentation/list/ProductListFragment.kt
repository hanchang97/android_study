package com.example.shoppingapp.presentation.list

import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import com.example.shoppingapp.databinding.FragmentProductBinding
import com.example.shoppingapp.databinding.FragmentProfileBinding
import com.example.shoppingapp.presentation.BaseFragment
import com.example.shoppingapp.presentation.adapter.ProductListAdapter
import com.example.shoppingapp.presentation.profile.ProfileViewModel
import org.koin.android.ext.android.inject

internal class ProductListFragment: BaseFragment<ProductListViewModel, FragmentProductBinding>() {

    companion object{
        const val TAG = "ProductListFragment"
    }

    override val viewModel by inject<ProductListViewModel>()

    override fun getViewBinding(): FragmentProductBinding = FragmentProductBinding.inflate(layoutInflater)

    private val adapter = ProductListAdapter()

    private val startProductDetailForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
            //TODO 성공적으로 처리 완료 이후 동작

        }

    override fun observeData() = viewModel.productListStateLiveData.observe(this){
        when(it){
            is ProductListState.Uninitialized -> {
                initViews(binding)
            }
            is ProductListState.Loading -> {
                handleLoadingState()
            }
            is ProductListState.Success -> {
                handleSuccessState(it)
            }
            is ProductListState.Error -> {
                handErrorState()
            }
        }
    }

    private fun initViews(binding: FragmentProductBinding) = with(binding){
        recyclerView.adapter = adapter

        refreshLayout.setOnRefreshListener{
            viewModel.fetchData()
        }
    }

    private fun handleLoadingState() = with(binding){
        refreshLayout.isRefreshing = true
    }

    private fun handleSuccessState(state: ProductListState.Success) = with(binding){
        refreshLayout.isEnabled = state.productList.isNotEmpty()
        refreshLayout.isRefreshing = false

        if(state.productList.isEmpty()){
            emptyResultTextView.isGone = false
            recyclerView.isGone = true
        }
        else{
            emptyResultTextView.isGone = true
            recyclerView.isGone = false
            adapter.setProductList(state.productList){
                /*startProductDetailForResult.launch(
                    ProductDetailActivity.newIntent(requireContext(), it.id)
                )*/
                Toast.makeText(requireContext(), "Product Entity : ${it}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handErrorState(){
        Toast.makeText(requireContext(), "에러가 발생했습니다", Toast.LENGTH_SHORT).show()
    }
}