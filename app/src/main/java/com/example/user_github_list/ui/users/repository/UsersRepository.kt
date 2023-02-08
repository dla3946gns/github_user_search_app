package com.example.user_github_list.ui.users.repository

import com.example.user_github_list.data.GithubUserData
import com.example.user_github_list.data.UserData
import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.utils.ApiResult
import com.example.user_github_list.utils.Status
import javax.inject.Inject

class UsersRepository @Inject constructor(private val retrofitInterface: RetrofitInterface) {

    suspend fun getUsers(
        username: String,
        per_page: String,
        page: String
    ): ApiResult<GithubUserData> {
        return try {
            val response = retrofitInterface.getUsers(
                username,
                "indexd",
                "asc",
                per_page,
                page
            )
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