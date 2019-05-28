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

    fun add(item: T) {
        val positionStart = dataSet.size
        dataSet.add(item)
        notifyItemInserted(positionStart)
    }

    fun add(position: Int, item: T) {
        dataSet.add(position, item)
        notifyItemInserted(position)
    }

    fun add(list: Collection<T>) {
        val startPosition = dataSet.size
        dataSet.addAll(list)
        notifyItemRangeInserted(startPosition, list.size)
    }

    fun remove(item: T) {
        val position = dataSet.indexOf(item)
        if (position >= 0) removeAt(position)
    }

    fun removeAt(position: Int) {
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clear() {
        val size = itemCount
        dataSet.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun removeAll() {
        val size = dataSet.size
        if (size > 0) {
            for (i in 0 until size) {
                dataSet.removeAt(0)
            }
            notifyItemRangeRemoved(0, size)
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
        dataSet = savedInstanceState.getSerializable("dataSet") as ArrayList<T>
        notifyDataSetChanged()
    }
}