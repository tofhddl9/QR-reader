package com.lgtm.qr_reader.view.history

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.Dimension.DP
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(
    @Dimension(unit = DP)
    private val height: Int,
    @Dimension(unit = DP)
    private val margin: Int,
    @ColorInt
    private val color: Int,
) : RecyclerView.ItemDecoration() {

    private val paint = Paint()

    init {
        paint.color = color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = (parent.paddingStart + margin).toFloat()
        val right = (parent.width - parent.paddingEnd - margin).toFloat()

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = (child.bottom + params.bottomMargin).toFloat()
            val bottom = top + height

            c.drawRect(left, top, right, bottom, paint)
        }

    }

}