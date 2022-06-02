package com.hanchang97.mvvm_flow_hilt.di

import com.hanchang97.mvvm_flow_hilt.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(url:String, okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService =
        retrofit.create(ApiService :: class.java)
}