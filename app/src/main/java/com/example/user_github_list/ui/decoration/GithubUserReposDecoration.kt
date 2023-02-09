package com.example.user_github_list.ui.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.user_github_list.R

class GithubUserReposDecoration(val context: Context): ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemOffset = context.resources.getDimensionPixelSize(R.dimen.size_10)

        if (position == 0 || position == 1) {
            outRect.top = itemOffset
        } else {
            outRect.top = 0
        }

        outRect.left = itemOffset
        outRect.right = itemOffset
        outRect.bottom = itemOffset * 2
    }

}