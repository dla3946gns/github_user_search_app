package com.example.user_github_list.ui.repos

import com.example.user_github_list.data.GithubUserReposData
import com.example.user_github_list.data.RepoData
import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.utils.Status
import com.example.user_github_list.utils.ApiResult

class ReposRepository(private val retrofitInterface: RetrofitInterface) {

    suspend fun getRepos(
        username: String,
        per_page: String,
        page: String
    ): ApiResult<MutableList<RepoData>> {
        return try {
            val response = retrofitInterface.getUserRepos(username, per_page, page)
            if (response.isSuccessful) {
                ApiResult(Status.SUCCESS, response.body(), null)
            } else {
                ApiResult(Status.ERROR, null, null)
            }
        } catch (e: Exception) {
            ApiResult(Status.ERROR, null, null)
        }
    }

}