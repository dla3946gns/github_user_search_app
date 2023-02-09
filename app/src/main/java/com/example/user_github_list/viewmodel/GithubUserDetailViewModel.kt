package com.example.user_github_list.viewmodel

import androidx.lifecycle.*
import com.example.user_github_list.data.UserData
import com.example.user_github_list.remote.RetrofitInterface
import com.example.user_github_list.ui.users.repository.UserDetailRepository
import com.example.user_github_list.utils.ApiResult
import com.example.user_github_list.utils.ContentEvents
import com.example.user_github_list.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserDetailViewModel @Inject constructor(
    private val retrofitInterface: RetrofitInterface,
    private val repository: UserDetailRepository
): ViewModel() {

    private val username = MutableLiveData<String>()

    private val _githubUserDetail = MutableLiveData<ContentEvents<ApiResult<UserData>>>()
    val githubUserDetail: LiveData<ContentEvents<ApiResult<UserData>>> = _githubUserDetail

    fun setUserName(name: String) {
        username.postValue(name)
    }

    fun getGithubUserDetail(name: String) = viewModelScope.launch {
        _githubUserDetail.postValue(
            ContentEvents(
                ApiResult(
                    Status.ERROR,
                    null,
                    null
                )
            )
        )
        _githubUserDetail.postValue(
            ContentEvents(
                repository.getUserDetail(name)
            )
        )
    }

}