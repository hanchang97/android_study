/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hilt.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.android.hilt.LogApplication
import com.example.android.hilt.R
import com.example.android.hilt.data.Log
import com.example.android.hilt.data.LoggerDataSource
import com.example.android.hilt.data.LoggerLocalDataSource
import com.example.android.hilt.di.InMemoryLogger
import com.example.android.hilt.util.DateFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Fragment that displays the database logs.
 */
@AndroidEntryPoint
class LogsFragment : Fragment() {

    // ServiceLocator를 사용하는 대신 Hilt를 사용하여 이런 유형의 인스턴스를 생성하고 관리할 수 있다. LogsFragment에서 Hilt를 사용하려면 @AndroidEntryPoint 주석을 사용한다.
    // @AndroidEntryPoint 를 사용하면 Android Lifecycle을 따르는 의존성 컨테이너를 생성한다.
    // @AndroidEntryPoint를 사용하면 Hilt는 LogsFragment의 수명 주기에 연결된 종속 컨테이너(Dependency container)를 생성하고 LogsFragment에 인스턴스들을 주입한다.


    // Hilt의 @Inject를 이용하면 인스턴스들을 주입할 수 있다. @Inject 어노테이션이 붙은 필드들에 인스턴스가 주입된다.
    // 아래의 내용을 클래스 필드 주입이라고 한다 -> 더 이상 populateFields 메서드를 호출 할 필요가 없어졌다

   /* private lateinit var logger: LoggerLocalDataSource
    private lateinit var dateFormatter: DateFormatter*/  // 기존 코드


    //@Inject lateinit var logger: LoggerLocalDataSource
    @InMemoryLogger
    @Inject lateinit var logger: LoggerDataSource // -> LoggerInMemoryDataSource 인스턴스 삽입을 위해  InMemoryLogger  Qualifier를 사용해준다!!!!

    @Inject lateinit var dateFormatter: DateFormatter  // Hilt   ,   private 한 필드에는 주입되지 않는다!!!!



    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_logs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
        }
    }

   /* override fun onAttach(context: Context) {
        super.onAttach(context)

        populateFields(context)
    }

    private fun populateFields(context: Context) {
        logger = (context.applicationContext as LogApplication).serviceLocator.loggerLocalDataSource
        dateFormatter =
            (context.applicationContext as LogApplication).serviceLocator.provideDateFormatter()
    }*/
    // -> Inject로 주입을 해줘서 위 부분은 더이상 필요하지 않다!!!!

    override fun onResume() {
        super.onResume()

        logger.getAllLogs { logs ->
            recyclerView.adapter =
                LogsViewAdapter(
                    logs,
                    dateFormatter
                )
        }
    }
}

/**
 * RecyclerView adapter for the logs list.
 */
private class LogsViewAdapter(
    private val logsDataSet: List<Log>,
    private val daterFormatter: DateFormatter
) : RecyclerView.Adapter<LogsViewAdapter.LogsViewHolder>() {

    class LogsViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        return LogsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.text_row_item, parent, false) as TextView
        )
    }

    override fun getItemCount(): Int {
        return logsDataSet.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        val log = logsDataSet[position]
        holder.textView.text = "${log.msg}\n\t${daterFormatter.formatDate(log.timestamp)}"
    }
}
