package com.hanchang97.edittext_debounce

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)

        /*editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    flow {
                        emit(s.toString())
                    }.debounce(1000L).collect {
                        Log.d("AppTest", "debounce: ${it}")
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                //Log.d("AppTest", "text len : ${s.toString().length}")
            }

        })*/

        editText.textChangesToFlow().debounce(1500)
            .onEach {
                Log.d("AppTest", "debounce : ${it.toString()}")
            }.launchIn(lifecycleScope)


    }

    fun EditText.textChangesToFlow(): Flow<CharSequence?> {
        // flow 콜백 받기
        return callbackFlow<CharSequence?> {
            val listener = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    Unit
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // 값 내보내기
                    trySend(s).isSuccess
                }

                override fun afterTextChanged(s: Editable?) {
                    Unit
                }
            }

            // 리스너 달아주기
            addTextChangedListener(listener)

            // 콜백이 사라질 때 실행됨
            awaitClose {
                removeTextChangedListener(listener)
            }

        }.onStart {
            emit(text)  // EidtText의 getText
        }
    }
}