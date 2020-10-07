/*
 * Copyright (c) 2020. The Axisnet Project.
 *
 * Licensed under XL Axiata Inc.
 * Software distributed under the license .
 */

package digital.klik.helper.component.custom.recyclerview

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(space: Int): RecyclerView.ItemDecoration() {

    var halfSpace: Int = space/2

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (parent.paddingLeft != halfSpace) {
            parent.run {
                setPadding(halfSpace, halfSpace, halfSpace, halfSpace)
                clipToPadding = false
            }
        }

        outRect.run {
            top = halfSpace
            bottom = halfSpace
            left = halfSpace
            right = halfSpace
        }

    }


}