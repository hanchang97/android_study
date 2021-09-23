package com.example.shoppingapp.data.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal fun provideProductApiService(retrofit: Retrofit): ProductApiService{
    return retrofit.create(ProductApiService::class.java)
}

internal fun provideProductRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit{
    return Retrofit.Builder()
        .baseUrl(Url.PRODUCT_BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)  // client는 인터셉터 등 네트워크 사용에 필요한 옵션들을 담는다
        .build()
}

internal fun provideGsonConverterFactory(): GsonConverterFactory{
    return GsonConverterFactory.create(
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    )
}

internal fun buildOkHttpClient(): OkHttpClient{
    val intercepter = HttpLoggingInterceptor()
    if(BuildConfig.DEBUG){
        intercepter.level = HttpLoggingInterceptor.Level.BODY  // 통신 로그 찍기 위함
    }
    else{
        intercepter.level = HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)  // 5초 내에 서버에 연결 x 이면 해당 요청을 실패로 간주
        .addInterceptor(intercepter)
        .build()
}


