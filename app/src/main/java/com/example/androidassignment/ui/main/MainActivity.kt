package com.example.androidassignment.ui.main

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassignment.R
import com.example.androidassignment.adapter.IssueRVAdapter
import com.example.androidassignment.base.BaseActivity
import com.example.androidassignment.data.remote.GithubResponse
import com.example.androidassignment.databinding.ActivityMainBinding
import com.example.androidassignment.databinding.InputDialogBinding
import com.example.androidassignment.network.GithubAPI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    {ActivityMainBinding.inflate(it)}
) {
    @Inject
    lateinit var retrofit: GithubAPI
    private val listLiveData = MutableLiveData<ArrayList<GithubResponse>>()
    private lateinit var adapter : IssueRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = IssueRVAdapter(this@MainActivity)
        binding.nameTv.setOnClickListener{
            setUpDialog(this@MainActivity, R.layout.input_dialog)
        }

        binding.githubRv.layoutManager = LinearLayoutManager(this)

        listLiveData.observe(this) {
            adapter.update(it)
            binding.githubRv.adapter = adapter
        }
    }

    private fun setUpDialog(context: Context, layout: Int) {
        val dialog = Dialog(context)
        when(layout){
            R.layout.input_dialog -> {
                val binding = InputDialogBinding.inflate(layoutInflater)
                dialog.setContentView(binding.root)
                dialog.show()
                binding.positiveBtn.setOnClickListener{
                    if(binding.orgEt.text.toString() != "" || binding.repoEt.text.toString() != ""){
                        apiCall(binding.orgEt.text.toString(), binding.repoEt.text.toString())
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, getString(R.string.input_toast), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun apiCall(org: String, repo: String){
         lifecycleScope.launch {
             val response = retrofit.getIssueList(org, repo)
             if(response.isSuccessful){
                 if(response.code() == 200){
                     listLiveData.postValue(response.body())
                 }
             }
        }
    }
}