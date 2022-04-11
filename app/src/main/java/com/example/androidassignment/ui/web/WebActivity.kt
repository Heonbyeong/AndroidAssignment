package com.example.androidassignment.ui.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidassignment.R
import com.example.androidassignment.base.BaseActivity
import com.example.androidassignment.databinding.ActivityWebBinding

class WebActivity : BaseActivity<ActivityWebBinding>(
    { ActivityWebBinding.inflate(it)}
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}