package com.example.androidassignment.data.local

import androidx.room.*

interface IssueDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repository: Issue)

    @Update
    suspend fun update(repository: Issue)

    @Delete
    suspend fun delete(repository: Issue)

    @Query("SELECT * FROM issue_table")
    suspend fun getAll(): ArrayList<Issue>
}