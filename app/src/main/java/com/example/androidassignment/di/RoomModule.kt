package com.example.androidassignment.di

import android.content.Context
import androidx.room.Room
import com.example.androidassignment.data.local.IssueDao
import com.example.androidassignment.data.local.IssueDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : IssueDatabase =
        Room.databaseBuilder(context, IssueDatabase::class.java,
        "issue-database").build()

    @Provides
    @Singleton
    fun provideIssueDao(db: IssueDatabase): IssueDao =
        db.issueDao()
}