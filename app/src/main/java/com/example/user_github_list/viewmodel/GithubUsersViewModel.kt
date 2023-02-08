package com.example.user_github_list.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.user_github_list.data.GithubUserData
import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.ui.users.paging.GithubUsersPaging
import com.example.user_github_list.ui.users.repository.UsersRepository
import com.example.user_github_list.utils.ApiResult
import com.example.user_github_list.utils.ContentEvents
import com.example.user_github_list.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUsersViewModel @Inject constructor(
    private val retrofitInterface: RetrofitInterface,
    private val repository: UsersRepository
): ViewModel() {

    private val query = MutableLiveData<String>()

    val list = query.switchMap { query ->
        Log.d("TAG", "setQuery: " + query)
        Pager(PagingConfig(pageSize = 30)) {
            GithubUsersPaging(query, retrofitInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    private val _githubUsers = MutableLiveData<ContentEvents<ApiResult<GithubUserData>>>()
    val githubUsers: LiveData<ContentEvents<ApiResult<GithubUserData>>> = _githubUsers

    fun setQuery(s: String) {
        query.postValue(s)
    }

    fun getGithubUsers(s: String) = viewModelScope.launch {
        _githubUsers.postValue(
            ContentEvents(
                ApiResult(
                    Status.LOADING,
                    null,
                    null
                )
            )
        )
        _githubUsers.postValue(
            ContentEvents(
                repository.getUsers(s, "30", "1")
            )
        )
    }

}