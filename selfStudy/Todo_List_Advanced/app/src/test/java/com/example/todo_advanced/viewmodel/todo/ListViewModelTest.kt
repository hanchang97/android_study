package com.example.todo_advanced.viewmodel.todo

import org.junit.Before

// 추가한 데이터가 리스트로 잘 보이는지 테스트 / ListViewModel 을 테스트 하기 위한 Unit Test Class

/*
*  1. initData() : mocking data넣고 잘 불러오는지 테스트
*  2. viewModel 에서 fetch 함수 실행 시 잘 불러는지 테스트
*  3. test Item Update
*  4. test Item Delete All
* */
internal class ListViewModelTest {
    // 필요한 유스케이스
    // 1. InsertToDoListUseCase
    // 2. GetToDoItemUseCase

    @Before
    fun init(){
        initData()
    }

    private fun initData() =

}