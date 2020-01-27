package me.himanshusoni.androidessentials.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.row_multi_selection.view.*
import kotlinx.android.synthetic.main.row_recycler_view.view.*
import kotlinx.android.synthetic.main.row_single_selection.view.*
import me.himanshusoni.androidessentials.R
import me.himanshusoni.androidessentials.data.Item
import me.himanshusoni.androidessentials.data.ItemProvider
import me.himanshusoni.androidessentials.recyclerview.adapter.BaseRecyclerViewAdapter
import me.himanshusoni.androidessentials.recyclerview.adapter.MultiChoiceRecyclerViewAdapter
import me.himanshusoni.androidessentials.recyclerview.adapter.SingleChoiceRecyclerViewAdapter
import me.himanshusoni.androidessentials.recyclerview.model.BaseViewHolder
import me.himanshusoni.androidessentials.ui.base.BaseAppCompatActivity

class RecyclerViewActivity : BaseAppCompatActivity() {

    private val mListAdapter = RecyclerViewAdapter(ArrayList())
    private val mMultiAdapter = MultiRecyclerViewAdapter(ArrayList())
    private val mSingleAdapter = SingleRecyclerViewAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setToolbar(toolbar)

        setDrawerLayout(drawerLayout)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_recycler_view -> {
                    recyclerView.adapter = mListAdapter
                }
                R.id.nav_multi_selection -> {
                    recyclerView.adapter = mMultiAdapter
                }
                R.id.nav_single_selection -> {
                    recyclerView.adapter = mSingleAdapter
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        recyclerView.layoutManager = LinearLayoutManager(this)


        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        } else {
            mListAdapter.add(ItemProvider.items)
            mMultiAdapter.add(ItemProvider.items)
            mSingleAdapter.add(ItemProvider.items)

            recyclerView.adapter = mListAdapter
            navView.setCheckedItem(R.id.nav_recycler_view)
        }


//        Timer().scheduleAtFixedRate(
//            object : TimerTask() {
//                override fun run() {
//                    runOnUiThread {
//                        //mListAdapter.replaceItems(ItemProvider.items) { i1, i2 -> i1.name == i2.name }
//                    }
//                }
//            }, 5000, 2000
//        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        mListAdapter.onRestoreInstanceState(savedInstanceState)
        mMultiAdapter.onRestoreInstanceState(savedInstanceState)
        mSingleAdapter.onRestoreInstanceState(savedInstanceState)

        val checkedItem = savedInstanceState.getInt("checkedItem", R.id.nav_recycler_view)

        navView.setCheckedItem(checkedItem)
        recyclerView.adapter = when (checkedItem) {
            R.id.nav_recycler_view -> mListAdapter
            R.id.nav_single_selection -> mSingleAdapter
            R.id.nav_multi_selection -> mMultiAdapter
            else -> mListAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("checkedItem", navView.checkedItem?.itemId ?: -1)

        mListAdapter.onSaveInstanceState(outState)
        mMultiAdapter.onSaveInstanceState(outState)
        mSingleAdapter.onSaveInstanceState(outState)
    }

    private inner class RecyclerViewAdapter(list: ArrayList<Item>) :
        BaseRecyclerViewAdapter<Item, RecyclerViewAdapter.ViewHolder>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_recycler_view, parent, false)
            return ViewHolder(v)
        }

        internal inner class ViewHolder(root: View) : BaseViewHolder<Item>(root) {
            override fun bind(item: Item) {
                itemView.tvText.text = item.name
            }
        }
    }

    private inner class MultiRecyclerViewAdapter(list: ArrayList<Item>) :
        MultiChoiceRecyclerViewAdapter<Item, MultiRecyclerViewAdapter.ViewHolder>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_multi_selection, parent, false)
            return ViewHolder(v)
        }

        internal inner class ViewHolder(root: View) : BaseViewHolder<Item>(root) {
            override fun bind(item: Item) {
                itemView.cbText.isChecked = isSelected(adapterPosition)
                itemView.cbText.text = item.name

                itemView.cbText.setOnCheckedChangeListener { _, isChecked ->
                    setSelected(adapterPosition, isChecked, false)
                }
            }
        }
    }

    private inner class SingleRecyclerViewAdapter(list: ArrayList<Item>) :
        SingleChoiceRecyclerViewAdapter<Item, SingleRecyclerViewAdapter.ViewHolder>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_single_selection, parent, false)
            return ViewHolder(v)
        }

        internal inner class ViewHolder(root: View) : BaseViewHolder<Item>(root) {
            override fun bind(item: Item) {
                itemView.rbText.isChecked = getSelectedItemPosition() == adapterPosition
                itemView.rbText.text = item.name

                itemView.rbText.setOnClickListener {
                    setSelected(adapterPosition, true)
                }
            }
        }
    }
}
