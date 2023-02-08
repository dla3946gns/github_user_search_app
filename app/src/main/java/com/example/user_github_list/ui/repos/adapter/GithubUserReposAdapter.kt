package com.example.user_github_list.ui.repos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.user_github_list.BR
import com.example.user_github_list.data.RepoData
import com.example.user_github_list.databinding.ItemGithubUserReposBinding

class GithubUserReposAdapter: PagingDataAdapter<RepoData, GithubUserReposAdapter.GithubUserRepoViewHolder>(
    DIFF_UTIL) {

    var onClick: ((String) -> Unit)? = null

    inner class GithubUserRepoViewHolder(val viewDataBinding: ItemGithubUserReposBinding): ViewHolder(viewDataBinding.root)

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<RepoData>() {
            override fun areItemsTheSame(oldItem: RepoData, newItem: RepoData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoData, newItem: RepoData): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onRepoClick(listener: (String) -> Unit) {
        onClick = listener
    }

    override fun onBindViewHolder(holder: GithubUserRepoViewHolder, position: Int) {
        val data = getItem(position)
        holder.viewDataBinding.setVariable(BR.repo, data)
        holder.viewDataBinding.root.setOnClickListener {
            onClick.let {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserRepoViewHolder {
        val binding = ItemGithubUserReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubUserRepoViewHolder(binding)
    }

}