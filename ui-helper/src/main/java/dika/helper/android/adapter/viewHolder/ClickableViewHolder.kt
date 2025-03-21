package dika.helper.android.adapter.viewHolder

import android.view.View
import dika.helper.android.adapter.listener.OnSelectedItemListener


abstract class ClickableViewHolder<T>(
    itemView: View,
    private val onSelectedItemListener: OnSelectedItemListener<T>?
): BaseViewHolder<T>(itemView) {

    final override fun bind(data: T) {
        bind(data, itemView)

        if (onSelectedItemListener != null) {
            itemView.setOnClickListener {
                onSelectedItemListener.onSelectedItem(data)
            }
        }
    }

    abstract fun bind(data: T, view: View)
}