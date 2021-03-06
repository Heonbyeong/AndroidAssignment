package com.example.androidassignment.di

import com.example.androidassignment.network.GithubAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val CONNECT_TIME_OUT: Long = 15
    private const val WRITE_TIME_OUT: Long = 15
    private const val READ_TIME_OUT: Long = 15

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.github.com/repos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideGithubAPI(retrofit: Retrofit): GithubAPI =
        retrofit.create(GithubAPI::class.java)
}