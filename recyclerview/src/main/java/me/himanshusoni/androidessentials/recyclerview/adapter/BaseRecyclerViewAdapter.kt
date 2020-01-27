package me.himanshusoni.androidessentials.recyclerview.adapter

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.himanshusoni.androidessentials.recyclerview.model.BaseViewHolder
import java.util.*

abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<T>>(var dataSet: ArrayList<T>) :
    RecyclerView.Adapter<VH>() {

    final override fun getItemCount(): Int = dataSet.size

    final override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    fun getItem(position: Int): T = dataSet[position]

    fun replaceItems(newItems: List<T>, areItemsTheSame: (old: T, new: T) -> Boolean) {
        val oldData = dataSet
        dataSet.clear()
        dataSet.addAll(newItems)

        DiffUtil.calculateDiff(
            DiffCallback(oldData, newItems, diffCallback(areItemsTheSame))
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

    fun removeAll(notify: Boolean = true) {
        val size = dataSet.size
        if (size > 0) {
            for (i in 0 until size) {
                dataSet.removeAt(0)
            }
            if (notify) notifyItemRangeRemoved(0, size)
        }
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
        dataSet = savedInstanceState.getSerializable("dataSet") as ArrayList<T>
        notifyDataSetChanged()
    }
}

