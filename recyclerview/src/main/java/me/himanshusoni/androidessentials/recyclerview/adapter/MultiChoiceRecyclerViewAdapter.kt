package me.himanshusoni.androidessentials.recyclerview.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.SparseBooleanArray
import java.util.*
import kotlin.collections.HashMap

abstract class MultiChoiceRecyclerViewAdapter<T, VH : BaseViewHolder<T>>(dataSet: ArrayList<T>) :
    BaseRecyclerViewAdapter<T, VH>(dataSet) {

    private val mSelectedPositions: SparseBooleanArray = SparseBooleanArray()

    /**
     * @return Selected items count
     */
    fun getSelectedItemCount(): Int = getSelectedItems().size

    /**
     * Indicates the list of selected items
     *
     * @return List of selected items ids
     */
    fun getSelectedItemPositions(): List<Int> {
        val items = ArrayList<Int>()
        for (i in 0 until mSelectedPositions.size()) {
            if (mSelectedPositions.get(i)) items.add(mSelectedPositions.keyAt(i))
        }
        return items
    }

    /**
     * Indicates the list of selected items
     *
     * @return List of selected items ids
     */
    fun getSelectedItems(): List<T> {
        val items = ArrayList<T>()
        for (i in 0 until mSelectedPositions.size()) {
            val selectedPos = mSelectedPositions.keyAt(i)
            if (mSelectedPositions.get(selectedPos)) {
                items.add(getItem(selectedPos))
            }
        }
        return items
    }

    /**
     *
     * */
    fun setSelectedItems(selectedItemPositions: Collection<Int>, notify: Boolean = true) {
        selectedItemPositions.forEach { setSelected(it, true, notify) }
    }

    /**
     * Indicates if the item at position position is selected
     *
     * @param position Position of the item to check
     * @return true if the item is selected, false otherwise
     */
    fun isSelected(position: Int): Boolean = mSelectedPositions.get(position, false)

    /**
     * Toggle the selection status of the item at a given position
     *
     * @param position Position of the item to toggle the selection status for
     */
    fun toggleSelection(position: Int, notify: Boolean = true) {
        mSelectedPositions.put(position, !mSelectedPositions.get(position))
        if (notify) notifyItemChanged(position)
    }

    protected fun setSelected(position: Int, selected: Boolean, notify: Boolean = true) {
        mSelectedPositions.put(position, selected)
        if (notify) notifyItemChanged(position)
    }

    /**
     * Clear the selection status for all items
     */
    fun clearSelection(notify: Boolean = true) {
        val selection = getSelectedItemPositions()
        mSelectedPositions.clear()
        if (notify) for (i in selection) notifyItemChanged(i)
    }

    /**
     * Selects  all items
     */
    fun selectAll(notify: Boolean = true) {
        for (i in 0 until itemCount) {
            setSelected(i, true, notify)
        }
    }

    /**
     * deselects all items
     */
    fun deselectAll(notify: Boolean = true) {
        for (i in 0 until itemCount) {
            setSelected(i, false, notify)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable("selectedPositions", mSelectedPositions.toHashMap())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState?.containsKey("selectedPositions") == true) {
            @Suppress("UNCHECKED_CAST")
            val map = savedInstanceState.getSerializable("selectedPositions") as HashMap<Int, Boolean>
            map.forEach { (key, value) ->
                mSelectedPositions.put(key, value)
            }
            notifyDataSetChanged()
        }
    }
}

@SuppressLint("UseSparseArrays")
private fun SparseBooleanArray.toHashMap(): HashMap<Int, Boolean> {
    val map = HashMap<Int, Boolean>()
    for (i in 0 until this.size()) {
        map[this.keyAt(i)] = this.get(this.keyAt(i))
    }
    return map
}
