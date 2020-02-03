package com.android.baselibrary.extension

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun RecyclerView.bindFloatingActionButton(fab: FloatingActionButton) = this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0 && fab.isShown) {
            fab.hide()
        } else if (dy < 0 && !fab.isShown) {
            fab.show()
        }
    }

})

fun SnapHelper.snapToPosition(recyclerView: RecyclerView, position: Int) {
    recyclerView.apply {
        val view = findViewHolderForAdapterPosition(position)?.itemView
        val snapPositions = view?.let {
            layoutManager?.let { it1 -> calculateDistanceToFinalSnap(it1, it) }
        }

        snapPositions?.let { smoothScrollBy(it[0], it[1]) }
    }
}