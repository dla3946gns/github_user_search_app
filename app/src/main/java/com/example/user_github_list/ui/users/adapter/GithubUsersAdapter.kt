package com.example.user_github_list.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.user_github_list.BR
import com.example.user_github_list.data.UserData
import com.example.user_github_list.databinding.ItemGithubUsersBinding

class GithubUsersAdapter: PagingDataAdapter<UserData, GithubUsersAdapter.GithubUsersViewHolder>(
    DIFF_UTIL
) {

    var onClick: ((String) -> Unit)? = null

    inner class GithubUsersViewHolder(val viewDataBinding: ItemGithubUsersBinding): ViewHolder(viewDataBinding.root)

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun onUserClick(listener: (String) -> Unit) {
        onClick = listener
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        val data = getItem(position)
        holder.viewDataBinding.setVariable(BR.user, data)
        holder.viewDataBinding.root.setOnClickListener {
            onClick.let {
                if (data != null && data.login.isNotEmpty()) {
                    if (it != null) {
                        it(data.login)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUsersViewHolder {
        val binding = ItemGithubUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubUsersViewHolder(binding)
    }

}