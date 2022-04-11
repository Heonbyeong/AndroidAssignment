package com.example.androidassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Issue::class], version = 1)
abstract class IssueDatabase : RoomDatabase() {
    abstract fun issueDao() : IssueDao
}