package com.example.androidassignment.data.local

import androidx.room.Entity

@Entity(tableName = "issue_table")
data class Issue(
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