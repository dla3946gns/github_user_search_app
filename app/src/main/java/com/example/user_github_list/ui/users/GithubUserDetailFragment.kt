package com.example.user_github_list.ui.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.user_github_list.R
import com.example.user_github_list.databinding.FragmentGithubUserDetailBinding
import com.example.user_github_list.utils.Status
import com.example.user_github_list.viewmodel.GithubUserDetailViewModel
import com.google.android.material.tabs.TabLayout.TabIndicatorGravity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubUserDetailFragment: Fragment() {

    lateinit var binding: FragmentGithubUserDetailBinding
    val args: GithubUserDetailFragmentArgs by navArgs()
    val viewModel: GithubUserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getGithubUserDetail(args.username)
        viewModel.githubUserDetail.observe(viewLifecycleOwner) {
            when(it.getContentIfNotHandled()?.status) {
                Status.LOADING -> {
                    // 로딩바 보이기
                    binding.progressBar.isVisible = true
                }
                Status.ERROR -> {
                    // 로딩바 보이기
                    binding.progressBar.isVisible = false
                }
                Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    if (it.peekContent().data != null) {
                        binding.llUserDetail.isVisible = true
                        binding.userData = it.peekContent().data

                        // 프로필 이미지
                        Glide
                            .with(binding.ivProfile)
                            .load(binding.userData?.avatar_url)
                            .error(R.mipmap.ic_github)
                            .fallback(R.mipmap.ic_github)
                            .placeholder(R.mipmap.ic_github)
                            .into(binding.ivProfile)

                        binding.tvUsername.text = context?.resources?.getString(R.string.fmt_username, binding.userData?.name)
                        binding.tvGithubName.text = context?.resources?.getString(R.string.fmt_user_github_name, binding.userData?.login)
                        binding.tvRepositoryCount.text = context?.resources?.getString(R.string.fmt_repo_count, binding.userData?.public_repos)
                        binding.tvFollowerCount.text = context?.resources?.getString(R.string.fmt_follower_count, binding.userData?.followers)
                        binding.tvFollowingCount.text = context?.resources?.getString(R.string.fmt_follower_count, binding.userData?.following)
                    } else {
                        binding.llUserDetail.isVisible = false
                    }
                }
                else -> {
                    // 로딩바 안 보이기
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

}