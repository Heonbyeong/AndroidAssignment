package com.example.androidassignment.network

import com.example.androidassignment.data.remote.GithubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("{org}/{repo}/issues")
    suspend fun getIssueList(
        @Path("org") org : String,
        @Path("repo") repo : String
    ) : Response<ArrayList<GithubResponse>>
}