package digital.klik.helper.adapter

import android.view.View
import digital.klik.helper.adapter.listener.OnSelectedItemListener
import digital.klik.helper.adapter.viewHolder.ClickableViewHolder

abstract class ClickableRecyclerViewAdapter<T, VH: ClickableViewHolder<T>>: BaseRecyclerViewAdapter<T, VH>() {
    var onSelectedItemListener: OnSelectedItemListener<T>? = null

    abstract fun onCreateViewHolder(onSelectedItemListener: OnSelectedItemListener<T>?, view: View): VH

    final override fun onCreateViewHolder(view: View): VH {
        return onCreateViewHolder(onSelectedItemListener, view)
    }
}