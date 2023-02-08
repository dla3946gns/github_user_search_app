package com.example.user_github_list.data

/**
 * 깃허브 사용자 데이터
 *
 * @author Myeong Hoon Lim on 2023-02-08
 */
data class UserData (
    val login: String,
    val id: String,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: String,
    val score: String
)