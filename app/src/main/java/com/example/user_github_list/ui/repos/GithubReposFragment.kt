package com.example.user_github_list.ui.repos

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.user_github_list.databinding.FragmentGithubUserReposBinding
import com.example.user_github_list.ui.repos.adapter.GithubUserReposAdapter
import com.example.user_github_list.viewmodel.GithubUserReposViewModel
import com.example.user_github_list.viewmodel.GithubUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubReposFragment: Fragment() {

    lateinit var binding: FragmentGithubUserReposBinding
    val viewModel: GithubUserReposViewModel by viewModels()
    val githubUserReposAdapter = GithubUserReposAdapter()

}