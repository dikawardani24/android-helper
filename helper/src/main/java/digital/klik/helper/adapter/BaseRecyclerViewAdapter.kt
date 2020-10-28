package digital.klik.helper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import digital.klik.helper.adapter.viewHolder.BaseViewHolder

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
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(item: T) {
        items.remove(item)
        notifyDataSetChanged()
    }

    fun removeItems(items: List<T>) {
        this.items.removeAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return items.isNotEmpty()
    }
}