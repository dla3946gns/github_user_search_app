package com.example.user_github_list.ui.users.repository

import com.example.user_github_list.data.UserData
import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.utils.ApiResult
import com.example.user_github_list.utils.Status

class UserDetailRepository(private val retrofitInterface: RetrofitInterface) {

    suspend fun getUserDetail(
        username: String
    ): ApiResult<UserData> {
        return try {
            val response = retrofitInterface.getUserDetail(username)
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