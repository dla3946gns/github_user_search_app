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

    var onClickUserItem: ((String, String) -> Unit)? = null
    var onClickUserProfileImage: ((String) -> Unit)? = null
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

    fun onUserClick(listener: (String, String) -> Unit) {
        onClickUserItem = listener
    }

    fun onProfileClick(listener: (String) -> Unit) {
        onClickUserProfileImage = listener
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        val data = getItem(position)
        holder.viewDataBinding.apply {
            setVariable(BR.user, data)
            if (data != null && data.login.isNotEmpty()) {
                root.setOnClickListener {
                    onClickUserItem.let {
                        if (it != null) {
                            it(data.login, data.avatar_url)
                        }
                    }
                }
                ivProfile.setOnClickListener {
                    onClickUserProfileImage.let {
                        if (it != null) {
                            it(data.login)
                        }
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