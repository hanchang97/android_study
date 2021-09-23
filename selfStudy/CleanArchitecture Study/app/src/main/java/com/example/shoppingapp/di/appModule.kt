package com.example.shoppingapp.di

import com.example.shoppingapp.data.network.buildOkHttpClient
import com.example.shoppingapp.data.network.provideGsonConverterFactory
import com.example.shoppingapp.data.network.provideProductApiService
import com.example.shoppingapp.data.network.provideProductRetrofit
import com.example.shoppingapp.data.repository.DefaultProductRepository
import com.example.shoppingapp.data.repository.ProductRepository
import com.example.shoppingapp.domain.GetProductItemUseCase
import com.example.shoppingapp.domain.GetProductListUseCase
import com.example.shoppingapp.presentation.list.ProductListViewModel
import com.example.shoppingapp.presentation.main.MainViewModel
import com.example.shoppingapp.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{

    // ViewModels
    viewModel { MainViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { ProductListViewModel(get()) }

    // Coroutines Dispatcher
    single{Dispatchers.Main}
    single{Dispatchers.IO}

    // Repositories
    single<ProductRepository>{ DefaultProductRepository(get(), get())}

    // UseCases
    factory{ GetProductItemUseCase(get())}
    factory{ GetProductListUseCase(get())}


    single { provideGsonConverterFactory()}  // single은 한 번만 객체 생성 / factory는 호출 될 때마다 객체 생성
    single { buildOkHttpClient() }
    single { provideProductRetrofit(get(), get())}
    single { provideProductApiService(get())}
}
