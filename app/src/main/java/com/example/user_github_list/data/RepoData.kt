package com.example.user_github_list.data

/**
 * 깃허브 사용자 리포지토리 데이터
 *
 * @author Myeong Hoon Lim on 2023-02-08
 */
data class RepoData(
    val id: String,
    val name: String,
    val fullName: String,
    val owner: UserData,
    val description: String,
)