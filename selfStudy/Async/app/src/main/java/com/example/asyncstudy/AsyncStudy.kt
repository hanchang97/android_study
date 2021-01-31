package com.example.asyncstudy

/*
*
* 비동기 - Async
* 동기 - Sync
*
*  동기 -> 작업을 순서대로 진행 / 위에서부터 아래로 진행
*
*  비동기 -> Thread 만들어서 작업을 따로 처리
*
*                                 -> 작업 결과를 받는다
*  main thread ---------------------------------------------->
*                                   ㅣ
*                       작업---------   //다른 쓰레드
*
*   안드로이드에서 Async 다루는 방법
*       - AsyncTask 상속
*           - onPreExecute : 쓰레드 출발하기 전 할 작업
*           - doIntBackground : 쓰레드가 할 작업
*           - onProgressUpdate : 작업 하는 중간 중간 메인쓰레드로 진행 상황 알려준다 / 중간중간 메인쓰레드로 가는 것
*           - onPostExcute : 작업 다 마친 후 메인쓰레드로 돌아간다
*
*
*   Async 장점
*       - Main Thread를 기다리게 할 필요가 없다
*       - 네트워크 작업 시 유용
* */