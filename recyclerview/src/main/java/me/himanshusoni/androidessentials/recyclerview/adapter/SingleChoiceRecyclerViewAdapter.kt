package me.himanshusoni.androidessentials.recyclerview.adapter

import android.os.Bundle
import java.util.*

abstract class SingleChoiceRecyclerViewAdapter<T, VH : BaseViewHolder<T>>(dataSet: ArrayList<T>) :
    BaseRecyclerViewAdapter<T, VH>(dataSet) {

    private var mSelectedItemPosition: Int = -1

    fun getSelectedItemPosition(): Int = mSelectedItemPosition

    fun getSelectedItem(): T? = if (mSelectedItemPosition != -1) getItem(mSelectedItemPosition) else null

    fun setSelected(position: Int, notify: Boolean = true) {
        val oldPosition = mSelectedItemPosition
        mSelectedItemPosition = position

        if (notify) {
            notifyItemChanged(position)
            notifyItemChanged(oldPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("selectedPosition", mSelectedItemPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState?.containsKey("selectedPosition") == true) {
            mSelectedItemPosition = savedInstanceState.getInt("selectedPosition", -1)
            notifyDataSetChanged()
        }
    }
}
