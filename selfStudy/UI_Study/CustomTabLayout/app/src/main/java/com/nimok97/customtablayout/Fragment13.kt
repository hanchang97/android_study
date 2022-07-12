package com.nimok97.customtablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nimok97.customtablayout.databinding.FragmentTab13Binding

class Fragment13: Fragment() {

    private lateinit var binding: FragmentTab13Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab13, container, false)
        return binding.root
    }

}