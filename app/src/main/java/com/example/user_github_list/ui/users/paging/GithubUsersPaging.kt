package com.example.user_github_list.ui.users.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.user_github_list.data.UserData
import com.example.user_github_list.remote.RetrofitInterface

class GithubUsersPaging(
    val s: String,
    val retrofitInterface: RetrofitInterface
): PagingSource<Int, UserData>() {

    override fun getRefreshKey(state: PagingState<Int, UserData>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserData> {
        val page = params.key ?: 1
        var itemCount = 0
        var totalCount = 0

        return try {
            val data = retrofitInterface.getUsers(
                s,
                "indexed",
                "asc",
                "30",
                page.toString()
            )

            var dataList = mutableListOf<UserData>()

            if (data.body() != null) {
                dataList = data.body()?.items ?: mutableListOf()
                totalCount = data.body()?.total_count ?: 0
            }

            itemCount += dataList.size

            val nextKey =
            if (itemCount == totalCount) {
                null
            } else {
                page + 1
            }

            LoadResult.Page(
                data = dataList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}