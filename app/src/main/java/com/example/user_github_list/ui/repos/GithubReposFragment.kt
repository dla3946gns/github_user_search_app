package com.example.user_github_list.ui.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.user_github_list.R
import com.example.user_github_list.data.RepoData
import com.example.user_github_list.databinding.FragmentGithubUserReposBinding
import com.example.user_github_list.ui.decoration.GithubUserReposDecoration
import com.example.user_github_list.ui.repos.adapter.GithubUserReposAdapter
import com.example.user_github_list.ui.users.GithubUsersFragmentDirections
import com.example.user_github_list.ui.users.adapter.ListLoadStateAdapter
import com.example.user_github_list.utils.Extensions.navigateSafe
import com.example.user_github_list.viewmodel.GithubUserReposViewModel
import com.example.user_github_list.viewmodel.GithubUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubReposFragment: Fragment() {

    lateinit var binding: FragmentGithubUserReposBinding
    val args: GithubReposFragmentArgs by navArgs()
    val viewModel: GithubUserReposViewModel by viewModels()
    val githubUserReposAdapter = GithubUserReposAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubUserReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()

        if (args.username.isNotEmpty()) {
            viewModel.setUserName(args.username)

            binding.tvUsername.text = String.format(resources.getString(R.string.fmt_user_repository_desc), args.username)
        } else {
            viewModel.setUserName("")
        }

        binding.tvRestart.setOnClickListener {
            githubUserReposAdapter.retry()
        }

        githubUserReposAdapter.apply {
            addLoadStateListener { combinedLoadStates ->
                binding.apply {
                    progressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
                    rvUserRepos.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
                    llRestart.isVisible = combinedLoadStates.source.refresh is LoadState.Error

                    if (combinedLoadStates.source.refresh is LoadState.NotLoading
                        && combinedLoadStates.append.endOfPaginationReached
                        && itemCount < 1) {
                        rvUserRepos.isVisible = false
                        tvSearchResultNone.isVisible = true
                    } else {
                        rvUserRepos.isVisible = true
                        tvSearchResultNone.isVisible = false
                    }
                }
            }

            onRepoClick { username, repoName, desc ->
                val action = GithubReposFragmentDirections.actionGithubUserRepoFragmentToGithubUserDetailFragment(
                    "R",
                    username,
                    repoName,
                    desc
                )
                findNavController().navigateSafe(action.actionId, action.arguments)
            }
        }

        viewModel.list.observe(viewLifecycleOwner) {
            githubUserReposAdapter.submitData(lifecycle, it)
        }
    }

    private fun setRecyclerView() {
        binding.rvUserRepos.apply {
            adapter = githubUserReposAdapter.withLoadStateHeaderAndFooter(
                header = ListLoadStateAdapter { githubUserReposAdapter.retry() },
                footer = ListLoadStateAdapter { githubUserReposAdapter.retry() }
            )
            layoutManager = GridLayoutManager(context, 2)
            while (itemDecorationCount > 0) {
                removeItemDecorationAt(0)
            }
            addItemDecoration(GithubUserReposDecoration(context))
        }
    }

}