package com.example.networkstudy.NetworkStudy


/*
* Database <----> Server  <----> Client(app, web...)
*
* Local DataBase의 한계
* - 동기화가 어렵다
* - 상호작용이 불가능 하다
*  ex> 다른 사람의 데이터와 상호 작용 불가(ex>댓글)
*
*
* < 서버와 통신하는 방법>
*  - url을 통해 요청
*  - 인증정보를 보낸다
*  - JSON 형식을 사용해서 data를 보낸다
*  - JavaScript Object Notation -> JavaScript에서 객체를 만들 때 사용하는 표현식이다
*
*  < JSON 형식 >
*  [] -> List
*  {} -> 객체
*       -> "" = 문자열
*       -> ""없으면 = 숫자 -> 어떤 자료형 숫자인지는 서버 api 명세서를 보고 파악한다
*
* ex>  JSON response
*       [
           {
                "id": 1,
                "name": "홍길동",
                "age": 20,
                "intro": "나는 홍길동이다!"    //객체 1
            },
            {
                "id": 2,
                "name": "김아무개",
                "age": 10,
                "intro": "난 김아무개 입니다 :)"   //객체 2
            }
        ]

*
*
*   <JSON Parsing>
*   -> JSON을 kotlin or java가 이해할 수 있게 변형하는 과정
*
*   <Serializable> (직렬화)
*   -> 코틀린이나 자바가 이해할 수 있는 툴
*
* ex> class Person(
*       var id : Int? = null
*       var name : String? = null
*       var age : Int? = null
*       var intro : String? = null
*       )
*
*  < Request Type >
*  - GET -> 정보 요청
*  - POST -> 정보 추가 요청
*  - DELETE -> 정보 삭제 요청
*  - PUT -> 정보 수정 요청
*
*   < Status Code >
       보통 200번대 -> 처리가 잘 되었다
*
* */