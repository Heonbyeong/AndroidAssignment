package com.example.androidassignment.ui.main

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidassignment.R
import com.example.androidassignment.ui.adapter.IssueRVAdapter
import com.example.androidassignment.base.BaseActivity
import com.example.androidassignment.data.remote.GithubResponse
import com.example.androidassignment.databinding.ActivityMainBinding
import com.example.androidassignment.databinding.ErrorDialogBinding
import com.example.androidassignment.databinding.InputDialogBinding
import com.example.androidassignment.network.GithubAPI
import com.example.androidassignment.ui.issue.IssueActivity
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
            setUpDialog(R.layout.input_dialog)
        }

        binding.githubRv.layoutManager = LinearLayoutManager(this)

        listLiveData.observe(this) {
            adapter.update(it)
            binding.githubRv.adapter = adapter

            adapter.setOnItemClickListener(object: IssueRVAdapter.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    val intent = Intent(this@MainActivity, IssueActivity::class.java).apply {
                        putExtra("profile", listLiveData.value!![position].user.avatar_url)
                        putExtra("name", listLiveData.value!![position].user.login)
                        putExtra("body", listLiveData.value!![position].body)
                        putExtra("issue", listLiveData.value!![position].number.toString())
                    }
                    startActivity(intent)
                }
            })
        }
    }

    private fun setUpDialog(layout: Int) {
        val dialog = Dialog(this)
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
            R.layout.error_dialog -> {
                val binding = ErrorDialogBinding.inflate(layoutInflater)
                dialog.setContentView(binding.root)
                dialog.show()
                binding.positiveBtn.setOnClickListener{
                    dialog.dismiss()
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
             } else {
                 setUpDialog(R.layout.error_dialog)
             }
        }
    }
}