package com.example.user_github_list.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.user_github_list.R
import com.example.user_github_list.databinding.FragmentGithubDetailBinding
import com.example.user_github_list.utils.Status
import com.example.user_github_list.viewmodel.GithubUserDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubDetailFragment: Fragment() {

    lateinit var binding: FragmentGithubDetailBinding
    val args: GithubDetailFragmentArgs by navArgs()
    val viewModel: GithubUserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (args.detailType == "U") {
            binding.progressBar.isVisible = true
            binding.nestedScrollView.isVisible = false
            binding.llRestart.isVisible = false

            args.username?.let { viewModel.getGithubUserDetail(it) }
            viewModel.githubUserDetail.observe(viewLifecycleOwner) {
                when(it.getContentIfNotHandled()?.status) {
                    Status.LOADING -> {
                        // 로딩바 보이기
                        binding.progressBar.isVisible = true
                    }
                    Status.ERROR -> {
                        binding.llRestart.isVisible = false
                    }
                    Status.SUCCESS -> {
                        if (it.peekContent().data != null) {
                            binding.llRestart.isVisible = false
                            binding.nestedScrollView.isVisible = true
                            binding.progressBar.isVisible = false

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
                            binding.progressBar.isVisible = false
                            binding.nestedScrollView.isVisible = false
                        }
                    }
                    else -> {
                        // 로딩바 안 보이기
                        binding.progressBar.isVisible = false
                    }
                }
            }
        } else {

            binding.tvUsername.text = context?.resources?.getString(R.string.fmt_username, args.username)
            binding.tvGithubName.text = context?.resources?.getString(R.string.fmt_repo, args.repoName)
            binding.tvRepositoryCount.text = context?.resources?.getString(R.string.fmt_repo_desc, args.repoDesc)

            binding.progressBar.isVisible = false
            binding.llRestart.isVisible = false
        }
    }

}