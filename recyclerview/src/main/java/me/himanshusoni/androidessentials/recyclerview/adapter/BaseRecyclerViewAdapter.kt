package me.himanshusoni.androidessentials.recyclerview.adapter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import java.util.*


abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(var dataSet: ArrayList<T>) :
    RecyclerView.Adapter<VH>() {

    fun getItem(position: Int): T {
        return dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
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