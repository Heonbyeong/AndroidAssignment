package com.example.androidassignment.data.local

import androidx.room.Entity

@Entity(tableName = "issue_table")
data class Issue(
    val body: String
)