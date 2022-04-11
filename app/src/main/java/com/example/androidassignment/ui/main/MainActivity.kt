package com.example.androidassignment.ui.main

import android.os.Bundle
import com.example.androidassignment.base.BaseActivity
import com.example.androidassignment.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(
    {ActivityMainBinding.inflate(it)}
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}