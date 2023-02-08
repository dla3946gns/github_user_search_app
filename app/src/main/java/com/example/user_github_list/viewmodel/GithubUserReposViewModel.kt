package com.example.user_github_list.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.user_github_list.data.GithubUserReposData
import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.ui.repos.ReposRepository
import com.example.user_github_list.ui.repos.paging.GithubUserReposPaging
import com.example.user_github_list.utils.ApiResult
import com.example.user_github_list.utils.ContentEvents
import com.example.user_github_list.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserReposViewModel @Inject constructor(
    private val retrofitInterface: RetrofitInterface,
    private val repository: ReposRepository
): ViewModel() {

    private val username = MutableLiveData<String>()

    val list = username.switchMap { username ->
        Log.d("TAG", "setUserName: " + username)

        Pager(PagingConfig(pageSize = 30)) {
            GithubUserReposPaging(username, retrofitInterface)
        }.liveData.cachedIn(viewModelScope)
    }

    private val _githubUserRepos = MutableLiveData<ContentEvents<ApiResult<GithubUserReposData>>>()
    val githubUserRepos: LiveData<ContentEvents<ApiResult<GithubUserReposData>>> = _githubUserRepos

    fun setUserName(name: String) {
        username.postValue(name)
    }

    fun getGithubUserRepos(name: String) = viewModelScope.launch {
        _githubUserRepos.postValue(
            ContentEvents(
                ApiResult(
                    Status.LOADING,
                    null,
                    null
                )
            )
        )
        _githubUserRepos.postValue(
            ContentEvents(
                repository.getRepos(name, "30", "1")
            )
        )
    }

}