package me.himanshusoni.androidessentials.recyclerview.adapter

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.himanshusoni.androidessentials.recyclerview.model.BaseViewHolder

abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<T>>(
    list: List<T> = emptyList(),
    private val diffCallback: DiffUtil.ItemCallback<T> = diffCallback(
        areContentsTheSame = { t1, t2 -> t1 == t2 },
        areItemsTheSame = { t1, t2 -> t1 == t2 })
) : RecyclerView.Adapter<VH>() {

    val dataSet: ArrayList<T> = ArrayList(list)

    override fun getItemCount(): Int = dataSet.size

    fun getItem(position: Int): T = dataSet[position]

    final override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    @Suppress("UNCHECKED_CAST")
    fun replaceItems(newItems: List<T>) {
        val oldData = dataSet.clone() as List<T>
        dataSet.clear()
        dataSet.addAll(newItems)

        DiffUtil.calculateDiff(
            DiffCallback(oldData, newItems, diffCallback)
        ).dispatchUpdatesTo(this)
    }

    fun add(item: T, notify: Boolean = true) {
        val positionStart = dataSet.size
        dataSet.add(item)
        if (notify) notifyItemInserted(positionStart)
    }

    fun add(position: Int, item: T, notify: Boolean = true) {
        dataSet.add(position, item)
        if (notify) notifyItemInserted(position)
    }

    fun add(list: Collection<T>, notify: Boolean = true) {
        val startPosition = dataSet.size
        dataSet.addAll(list)
        if (notify) notifyItemRangeInserted(startPosition, list.size)
    }

    fun remove(item: T, notify: Boolean = true) {
        val position = dataSet.indexOf(item)
        if (position >= 0) removeAt(position, notify)
    }

    fun removeAt(position: Int, notify: Boolean = true) {
        dataSet.removeAt(position)
        if (notify) notifyItemRemoved(position)
    }

    fun clear(notify: Boolean = true) {
        val size = itemCount
        dataSet.clear()
        if (notify) notifyItemRangeRemoved(0, size)
    }

    fun isEmpty(): Boolean = dataSet.isEmpty()

    open fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable("dataSet", dataSet)
    }

    open fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null || !savedInstanceState.containsKey("dataSet")) {
            return
        }
        @Suppress("UNCHECKED_CAST")
        dataSet.addAll(savedInstanceState.getSerializable("dataSet") as ArrayList<T>)
        notifyDataSetChanged()
    }
}

