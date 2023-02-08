package com.example.user_github_list.remote

import com.example.user_github_list.data.GithubUserData
import com.example.user_github_list.data.GithubUserReposData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") q: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ): Response<GithubUserData>

    @GET("{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Query("per_page") per_page: String,
        @Query("page") page: String
    ): Response<GithubUserReposData>

}