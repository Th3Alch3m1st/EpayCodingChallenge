package com.epay.codingchallenge.utils

import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.epay.codingchallenge.R
import com.google.android.material.card.MaterialCardView

/**
 * Created By Rafiqul Hasan
 */
class GridItemDecoration constructor(private val spacing: Int, private val spanCount: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).absoluteAdapterPosition
        if (spanCount == 2) {
            outRect.left = if (position % 2 == 0) spacing else spacing / 2
            outRect.right = if (position % 2 == 1) spacing else spacing / 2
            outRect.top = if (position == 0 || position == 1) 0 else spacing
            outRect.bottom = 0

        } else if (spanCount == 3) {
            outRect.left = if (position % 3 == 0) spacing else spacing / 2
            if (position % 3 == 1) {
                outRect.left = spacing / 2
                outRect.right = spacing / 2
            }
            outRect.right = if (position % 3 == 2) spacing else spacing / 2
            outRect.top = if (position == 0 || position == 1 || position == 2) 0 else spacing
            outRect.bottom = 0
        }

        val rowPosition = position / spanCount
        if (view is MaterialCardView) {
            if (rowPosition % 2 == 0) {
                view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.teal_700))
            } else {
                view.setCardBackgroundColor(ContextCompat.getColor(view.context, R.color.purple_300))
            }
        }

    }
}