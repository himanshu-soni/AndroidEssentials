package me.himanshusoni.androidessentials.recyclerview.adapter

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T>(
    private val oldData: List<T>,
    private val newData: List<T>,
    private val itemCallback: DiffUtil.ItemCallback<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size
    override fun getNewListSize(): Int = newData.size
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        itemCallback.areContentsTheSame(oldData[oldItemPosition], newData[newItemPosition])

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        itemCallback.areItemsTheSame(oldData[oldItemPosition], newData[newItemPosition])

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}

inline fun <T> diffCallback(
    crossinline areItemsTheSame: (old: T, new: T) -> Boolean,
    crossinline areContentsTheSame: (old: T, new: T) -> Boolean = { old, new -> old == new }
): DiffUtil.ItemCallback<T> = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areItemsTheSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = areContentsTheSame(oldItem, newItem)
}
