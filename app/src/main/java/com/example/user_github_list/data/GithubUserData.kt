package com.example.user_github_list.data

/**
 * 검색 결과 깃허브 사용자 데이터
 *
 * @author Myeong Hoon Lim on 2023-02-08
 */
data class GithubUserData (
    val total_count: Int,
    val items: MutableList<UserData>
)