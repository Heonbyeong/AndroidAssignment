package com.example.androidassignment.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidassignment.data.remote.GithubResponse
import com.example.androidassignment.databinding.GithubItemBinding
import com.example.androidassignment.databinding.ImageItemBinding
import java.lang.RuntimeException

class IssueRVAdapter(private val activity: Activity): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val items: ArrayList<GithubResponse> = ArrayList()
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    private var onItemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    private val ISSUE = 0
    private val IMAGE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ISSUE -> {
                val binding = GithubItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
                return IssueViewHolder(binding)
            }
            IMAGE -> {
                val binding = ImageItemBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
                return ImageViewHolder(binding)
            }
            else -> throw RuntimeException("알 수 없는 ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(holder) {
            is IssueViewHolder -> holder.apply { bind(item) }
            is ImageViewHolder -> holder.apply { bind() }
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            4 -> IMAGE
            else -> ISSUE
        }
    }

    inner class IssueViewHolder(v : GithubItemBinding) : RecyclerView.ViewHolder(v.root) {
        val view = v
        fun bind(item : GithubResponse) {
            view.issueTv.text = "#${item.number}: ${item.title}"

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.setOnClickListener{
                    onItemClickListener?.onItemClick(itemView, position)
                }
            }
        }
    }

    inner class ImageViewHolder(v : ImageItemBinding) : RecyclerView.ViewHolder(v.root) {
        val view = v
        @SuppressLint("CheckResult")
        fun bind() {
            view.hellobotIv.clipToOutline = true
            Glide.with(activity)
                .load(Uri.parse("https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"))
                .into(view.hellobotIv)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(items: ArrayList<GithubResponse>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}