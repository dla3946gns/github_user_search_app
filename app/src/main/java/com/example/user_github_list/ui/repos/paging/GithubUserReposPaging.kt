package com.example.user_github_list.ui.repos.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.user_github_list.data.RepoData
import com.example.user_github_list.remote.RetrofitInterface

class GithubUserReposPaging(
    val username: String,
    val retrofitInterface: RetrofitInterface
): PagingSource<Int, RepoData>() {

    override fun getRefreshKey(state: PagingState<Int, RepoData>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoData> {
        val page = params.key ?: 1

        return try {
            val data = retrofitInterface.getUserRepos(
                username,
                "30",
                page.toString()
            )

            var dataList = mutableListOf<RepoData>()

            if (data.body() != null) {
                dataList = data.body() ?: mutableListOf()
            }

            LoadResult.Page(
                data = dataList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (dataList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}