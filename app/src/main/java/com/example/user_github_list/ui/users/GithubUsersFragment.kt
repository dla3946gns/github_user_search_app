package com.example.user_github_list.ui.users

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.user_github_list.databinding.FragmentGithubUsersBinding
import com.example.user_github_list.ui.decoration.GithubUsersDecoration
import com.example.user_github_list.ui.users.adapter.GithubUsersAdapter
import com.example.user_github_list.ui.users.adapter.GithubUsersLoadStateAdapter
import com.example.user_github_list.viewmodel.GithubUsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubUsersFragment: Fragment() {

    lateinit var binding: FragmentGithubUsersBinding
    val viewModel: GithubUsersViewModel by viewModels()
    val githubUsersAdapter = GithubUsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGithubUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecyclerView()

        binding.apply {
            viewHeaderSearch.headerSearchImageView.setOnClickListener {
                actionSearch()
            }
            viewHeaderSearch.wordEditText.setOnEditorActionListener(object :
                OnEditorActionListener {
                override fun onEditorAction(
                    v: TextView?,
                    actionId: Int,
                    event: KeyEvent?
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        actionSearch()
                        return true
                    }
                    return false
                }
            })

            tvSearchResultNone.setOnClickListener {
                actionSearch()
            }

            viewModel.list.observe(viewLifecycleOwner) {
                githubUsersAdapter.submitData(lifecycle, it)
            }
        }

        githubUsersAdapter.apply {
            onUserClick {
                // 카테고리 리스트 fragment로 이동
            }

            addLoadStateListener { combinedLoadStates ->
                binding.apply {
                    progressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
                    rvGithubUsers.isVisible = combinedLoadStates.source.refresh is LoadState.NotLoading
                    llRestart.isVisible = combinedLoadStates.source.refresh is LoadState.Error

                    if (combinedLoadStates.source.refresh is LoadState.NotLoading
                        && combinedLoadStates.append.endOfPaginationReached
                        && githubUsersAdapter.itemCount < 1) {
                        rvGithubUsers.isVisible = false
                        tvSearchResultNone.isVisible = true
                    } else {
                        rvGithubUsers.isVisible = true
                        tvSearchResultNone.isVisible = false
                    }
                }
            }
        }
    }

    private fun setRecyclerView() {
        binding.rvGithubUsers.apply {
            adapter = githubUsersAdapter.withLoadStateHeaderAndFooter(
                header = GithubUsersLoadStateAdapter { githubUsersAdapter.retry() },
                footer = GithubUsersLoadStateAdapter { githubUsersAdapter.retry() }
            )
            while (itemDecorationCount > 0) {
                removeItemDecorationAt(0)
            }
            addItemDecoration(GithubUsersDecoration(context))
        }
    }

    private fun actionSearch() {
        githubUsersAdapter.submitData(lifecycle, PagingData.empty())

        if (binding.viewHeaderSearch.wordEditText.text != null
            && binding.viewHeaderSearch.wordEditText.text.toString().isNotEmpty()) {
            viewModel.setQuery(binding.viewHeaderSearch.wordEditText.text.toString())
        }

        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewHeaderSearch.wordEditText.windowToken, 0)
    }

}