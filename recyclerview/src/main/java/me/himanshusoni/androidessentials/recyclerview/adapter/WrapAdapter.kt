package me.himanshusoni.androidessentials.recyclerview.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.himanshusoni.androidessentials.recyclerview.model.BaseViewHolder

class WrapAdapter<T, VH : BaseViewHolder<T>>(adapter: BaseRecyclerViewAdapter<T, VH>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mHeaderViews: ArrayList<View>
    private val mFooterViews: ArrayList<View>
    private val mItemTypesOffset: HashMap<Class<*>, Int>

    private val mDataObserver: RecyclerView.AdapterDataObserver

    private lateinit var mWrappedAdapter: BaseRecyclerViewAdapter<T, VH>

    init {
        mHeaderViews = ArrayList()
        mFooterViews = ArrayList()
        mItemTypesOffset = HashMap()
        mDataObserver = object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                positionStart: Int,
                itemCount: Int
            ) {
                super.onItemRangeChanged(positionStart, itemCount)
                notifyItemRangeChanged(positionStart + headerCount, itemCount)
            }

            override fun onItemRangeInserted(
                positionStart: Int,
                itemCount: Int
            ) {
                super.onItemRangeInserted(positionStart, itemCount)
                notifyItemRangeInserted(positionStart + headerCount, itemCount)
            }

            override fun onItemRangeRemoved(
                positionStart: Int,
                itemCount: Int
            ) {
                super.onItemRangeRemoved(positionStart, itemCount)
                notifyItemRangeRemoved(positionStart + headerCount, itemCount)
            }

            override fun onItemRangeMoved(
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                val hCount = headerCount
                // TODO: No notifyItemRangeMoved method?
                notifyItemRangeChanged(fromPosition + hCount, toPosition + hCount + itemCount)
            }
        }
        setWrappedAdapter(adapter)
    }

    override fun getItemViewType(position: Int): Int {
        val hCount = headerCount
        return if (position < hCount) HEADERS_START + position else {
            val itemCount = mWrappedAdapter.itemCount
            if (position < hCount + itemCount) {
                adapterTypeOffset + mWrappedAdapter.getItemViewType(position - hCount)
            } else FOOTERS_START + position - hCount - itemCount
        }
    }

    private fun updateAdapter(adapter: BaseRecyclerViewAdapter<T, VH>) {
        if (mWrappedAdapter.itemCount > 0) {
            notifyItemRangeRemoved(headerCount, mWrappedAdapter.itemCount)
        }
        setWrappedAdapter(adapter)
        notifyItemRangeInserted(headerCount, mWrappedAdapter.itemCount)
    }

    private fun setWrappedAdapter(adapter: BaseRecyclerViewAdapter<T, VH>) {
        if (::mWrappedAdapter.isInitialized) {
            mWrappedAdapter.unregisterAdapterDataObserver(mDataObserver)
        }
        mWrappedAdapter = adapter
        val adapterClass: Class<*> = mWrappedAdapter.javaClass
        if (!mItemTypesOffset.containsKey(adapterClass)) putAdapterTypeOffset(adapterClass)
        mWrappedAdapter.registerAdapterDataObserver(mDataObserver)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when {
            viewType < HEADERS_START + headerCount -> StaticViewHolder(
                mHeaderViews[viewType - HEADERS_START]
            )
            viewType < FOOTERS_START + footerCount -> StaticViewHolder(
                mFooterViews[viewType - FOOTERS_START]
            )
            else -> {
                mWrappedAdapter.onCreateViewHolder(viewGroup, viewType - adapterTypeOffset)
            }
        }
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val hCount = headerCount
        if (position >= hCount && position < hCount + mWrappedAdapter.itemCount) {
            mWrappedAdapter.onBindViewHolder(
                viewHolder as VH,
                position - hCount
            )
        }
    }

    /**
     * Add a static view to appear at the start of the RecyclerView. Headers are displayed in the
     * order they were added.
     * @param view The header view to add
     */
    fun addHeaderView(view: View) {
        mHeaderViews.add(view)
        notifyItemInserted(headerCount)
    }

    /**
     * Add a static view to appear at the end of the RecyclerView. Footers are displayed in the
     * order they were added.
     * @param view The footer view to add
     */
    fun addFooterView(view: View) {
        mFooterViews.add(view)
        notifyItemInserted(headerCount + wrappedItemCount + footerCount)
    }

    fun removeHeaderView(view: View) {
        val index = mHeaderViews.indexOf(view)
        if (index != -1) {
            mHeaderViews.remove(view)
            notifyItemRemoved(index)
        }
    }

    fun removeFooterView(view: View) {
        val index = mFooterViews.indexOf(view)
        if (index != -1) {
            mFooterViews.remove(view)
            notifyItemRemoved(headerCount + wrappedItemCount + index)
        }
    }

    override fun getItemCount(): Int {
        return headerCount + footerCount + wrappedItemCount
    }

    /**
     * @return The item count in the underlying adapter
     */
    val wrappedItemCount: Int
        get() = mWrappedAdapter.itemCount

    /**
     * @return The number of header views added
     */
    val headerCount: Int
        get() = mHeaderViews.size

    /**
     * @return The number of footer views added
     */
    val footerCount: Int
        get() = mFooterViews.size


    private fun putAdapterTypeOffset(adapterClass: Class<*>) {
        mItemTypesOffset[adapterClass] = ITEMS_START + mItemTypesOffset.size * ADAPTER_MAX_TYPES
    }

    private val adapterTypeOffset: Int
        get() = mItemTypesOffset.getOrElse(mWrappedAdapter.javaClass, { 0 })


    private class StaticViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    companion object {
        private const val HEADERS_START = Int.MIN_VALUE
        private const val FOOTERS_START = Int.MIN_VALUE + 10
        private const val ITEMS_START = Int.MIN_VALUE + 20
        private const val ADAPTER_MAX_TYPES = 100
    }


}