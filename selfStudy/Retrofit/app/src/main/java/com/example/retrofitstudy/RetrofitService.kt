package com.example.retrofitstudy

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {  // 도메인 주소 뒤의 변하는 주소 부분들 관리한다

    // base url 뒷 부분 작성해준다
    @GET("json/students/")
    fun getStudentsList(): Call<ArrayList<Person>>   // Call 안에 넣어줘야 하는게 retrofit 사용 형식  / 응답으로 오는 데이터를 Call 안의 형식으로 사용하겠다는 뜻

    // post 방식1
    @POST("json/students/")  // <key, value>
    fun createStudent(
        @Body params : HashMap<String, Any>   // 해쉬맵 사용
    ) : Call<Person>   // 응답 = 방금 post 요청 보낸 Person class data

    // post 방식2
    @POST("json/students/")
    fun createStudent2(
        @Body person : Person
    ): Call<Person>
}