package com.example.user_github_list.ui.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.user_github_list.R

class GithubUsersDecoration(val context: Context): ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemIntervalSize = context.resources.getDimensionPixelSize(R.dimen.size_10)

        if (position == 0) {
            outRect.top = itemIntervalSize
        }
        outRect.bottom = itemIntervalSize
    }

}