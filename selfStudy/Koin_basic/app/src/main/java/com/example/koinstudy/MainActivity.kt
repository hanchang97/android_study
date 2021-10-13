package com.example.koinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koinstudy.`object`.ClassRoom
import com.example.koinstudy.`object`.Student
import com.example.koinstudy.`object`.Teacher
import com.example.koinstudy.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val teacher : Teacher by inject()
        val student : Student by inject()

        val class1 : ClassRoom by inject(named("cl1"))
        val class2 : ClassRoom by inject(named("cl2"))

        binding.tvTeacher1.text = teacher.name
        binding.tvStudent1.text = student.name

        binding.tvClass1.text = class1.name
        binding.tvClass2.text = class2.name
    }
}