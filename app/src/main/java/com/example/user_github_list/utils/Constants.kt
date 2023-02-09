package com.example.user_github_list.utils

/**
 * 공통으로 사용하는 상수 모음 object
 *
 * @author Myeong Hoon Lim on 2023-02-08
 */
object Constants {

    const val BASE_URL = "https://api.github.com"

    // Retrofit Client Header String [Start]
    const val HEADER_ACCEPT_KEY = "Accept"
    const val HEADER_ACCEPT_VALUE = "application/vnd.github+json"
    const val HEADER_AUTHORIZATION_KEY = "Authorization"
    const val HEADER_AUTHORIZATION_VALUE = "token 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"
    const val HEADER_API_VERSION_KEY = "X-GitHub-Api-Version"
    const val HEADER_API_VERSION_VALUE = "2022-11-28"
    // Retrofit Client Header String [End]

}