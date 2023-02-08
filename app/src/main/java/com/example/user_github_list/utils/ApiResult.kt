package com.example.user_github_list.utils

class ApiResult<out T>(val status: Status, val data: T?, message: String?) {
    companion object {
        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }
        fun <T> loading(message: String?): ApiResult<T> {
            return ApiResult(Status.LOADING, null, message)
        }
        fun <T> erorr(message: String?): ApiResult<T> {
            return ApiResult(Status.ERROR, null, message)
        }
    }
}

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}