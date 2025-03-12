package dika.helper.android.adapter

import android.view.View
import dika.helper.android.adapter.listener.OnSelectedItemListener
import dika.helper.android.adapter.viewHolder.ClickableViewHolder

abstract class ClickableRecyclerViewAdapter<T, VH: ClickableViewHolder<T>>: BaseRecyclerViewAdapter<T, VH>() {
    var onSelectedItemListener: OnSelectedItemListener<T>? = null

    abstract fun onCreateViewHolder(onSelectedItemListener: OnSelectedItemListener<T>?, view: View): VH

    final override fun onCreateViewHolder(view: View): VH {
        return onCreateViewHolder(onSelectedItemListener, view)
    }
}