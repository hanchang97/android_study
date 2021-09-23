package com.example.bookreviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bookreviewapp.data.api.BookApi
import com.example.bookreviewapp.data.models.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val apiKey = "인터파크 apikey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // retrofit test
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookApi::class.java)  //

        bookService.getBestSeller(apiKey)
            .enqueue(object: Callback<BestSellerDto>{
                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    // 성공 처리
                    if(response.isSuccessful.not()) { // 성공x 인 경우
                        Log.e(TAG, "not success!!")
                        return
                    }

                    response.body()?.let{
                        Log.d(TAG, it.toString())

                        it.books.forEach{ book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    // 실패 처리
                    Log.e(TAG, t.toString())
                }

            })

    }

    companion object{
        private const val TAG = "MainActivity"
    }
}