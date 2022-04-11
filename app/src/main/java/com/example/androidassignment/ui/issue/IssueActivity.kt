package com.example.androidassignment.ui.issue

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.androidassignment.R
import com.example.androidassignment.base.BaseActivity
import com.example.androidassignment.databinding.ActivityIssueBinding

class IssueActivity : BaseActivity<ActivityIssueBinding>(
    { ActivityIssueBinding.inflate(it)}
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent

        if(intent.hasExtra("issue")) {
            val title = intent.getStringExtra("issue")
            supportActionBar?.title = "#$title"
        }

        if(intent.hasExtra("profile")){
            binding.profileIv.clipToOutline = true
            Glide.with(this)
                .load(Uri.parse(intent.getStringExtra("profile")))
                .into(binding.profileIv)
        }

        if(intent.hasExtra("name")){
            binding.nameTv.text = intent.getStringExtra("name")
        }

        if(intent.hasExtra("body")){
            binding.detailTv.text = intent.getStringExtra("body")
        }
    }
}