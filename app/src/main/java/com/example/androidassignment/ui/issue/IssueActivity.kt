package com.example.androidassignment.ui.issue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidassignment.R
import com.example.androidassignment.base.BaseActivity
import com.example.androidassignment.databinding.ActivityIssueBinding

class IssueActivity : BaseActivity<ActivityIssueBinding>(
    { ActivityIssueBinding.inflate(it)}
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}