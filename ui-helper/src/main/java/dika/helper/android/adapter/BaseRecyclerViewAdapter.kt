package dika.helper.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dika.helper.android.adapter.viewHolder.BaseViewHolder

@Suppress("unused")
abstract class BaseRecyclerViewAdapter<T, VH: BaseViewHolder<T>>: RecyclerView.Adapter<VH>() {
    private val items = ArrayList<T>()
    protected abstract val resLayout: Int

    abstract fun onCreateViewHolder(view: View): VH

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(resLayout, parent, false)
        return onCreateViewHolder(view)
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    final override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.indexOf(item))
    }

    fun addItems(items: List<T>) {
        items.forEach { item ->
            addItem(item)
        }
    }

    fun removeItem(item: T) {
        val pos = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(pos)
    }

    fun removeItems(items: List<T>) {
        items.forEach { toRemoved ->
            removeItem(toRemoved)
        }
    }

    fun clearItems() {
        items.clear()
        notifyItemRangeRemoved(0, items.size)
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return items.isNotEmpty()
    }
}