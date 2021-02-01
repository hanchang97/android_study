package com.example.retrofitstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitstudy.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var activityMainBinding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        activityMainBinding = binding
        setContentView(activityMainBinding!!.root)


// http://mellowcode.org/json/students/
        val retrofit = Retrofit.Builder()
                .baseUrl("http://mellowcode.org/")   // 서버 도메인 주소
                .addConverterFactory(GsonConverterFactory.create())     // 원하는 데이터 타입으로 자동 변경된다
                .build()

        // retrofit service create
        val service = retrofit.create(RetrofitService::class.java)

        // GET 요청
        // 통신을 대기줄에 올린다
        service.getStudentsList().enqueue(object : Callback<ArrayList<Person>> {
            override fun onResponse(call: Call<ArrayList<Person>>, response: Response<ArrayList<Person>>) {
                if(response.isSuccessful){ // 통신이 잘 된 경우
                    val personList = response.body()
                    Log.d("retrofit_success","test : " + personList?.get(0)?.age)

                    val code = response.code()
                    Log.d("retrofit_response_code", "code : " + code)

                    val error = response.errorBody()
                    val header = response.headers()
                    Log.d("retrofit_header","header : " + header)
                }
            }

            override fun onFailure(call: Call<ArrayList<Person>>, t: Throwable) {  // 통신 실패 시 호출
                Log.d("retrofit failure","error")
            }

        })

        // POST 요청(1)
        /*val params = HashMap<String, Any>()
        params.put("name", "홍길동")
        params.put("age", 20)
        params.put("intro", "안녕하세요!")

        service.createStudent(params).enqueue(object : Callback<Person>{
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if(response.isSuccessful){
                    val person = response.body()
                    Log.d("retrofit_post_test", "name : " + person?.name)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {

            }

        }) */

        // POST 요청(2)
        val person = Person(name = "김김김", age = 23, intro = "3김 입니다")
        service.createStudent2(person).enqueue(object : Callback<Person>{
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if(response.isSuccessful){
                    val person = response.body()
                    Log.d("retrofit_post_test", "name : " + person?.name)
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {

            }

        })
    }
}