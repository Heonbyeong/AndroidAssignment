package com.example.androidassignment.data.remote

data class GithubResponse(
    val id: Int,
    val number: Int,
    val title: String,
    val user: User,
    val body: String) {
    data class User(
        val login: String,
        val id: Int,
        val avatar_url: String
    )
}
