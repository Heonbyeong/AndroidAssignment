package com.example.androidassignment.data.local

import androidx.room.Entity

@Entity(tableName = "github")
data class Repository(
    val image: String,
    val name: String,
    val body: String
)