package me.himanshusoni.androidessentials.ui

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
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
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        mListAdapter.onRestoreInstanceState(savedInstanceState)
        mMultiAdapter.onRestoreInstanceState(savedInstanceState)
        mSingleAdapter.onRestoreInstanceState(savedInstanceState)

        val checkedItem = savedInstanceState?.getInt("checkedItem") ?: R.id.nav_recycler_view

        navView.setCheckedItem(checkedItem)
        recyclerView.adapter = when (checkedItem) {
            R.id.nav_recycler_view -> mListAdapter
            R.id.nav_single_selection -> mSingleAdapter
            R.id.nav_multi_selection -> mMultiAdapter
            else -> mListAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("checkedItem", navView.checkedItem?.itemId ?: -1)

        mListAdapter.onSaveInstanceState(outState)
        mMultiAdapter.onSaveInstanceState(outState)
        mSingleAdapter.onSaveInstanceState(outState)
    }

    private inner class RecyclerViewAdapter(list: ArrayList<Item>) :
            BaseRecyclerViewAdapter<Item, RecyclerViewAdapter.ViewHolder>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_recycler_view, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)

            holder.itemView.tvText.text = item.name
        }

        internal inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {
            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {

            }
        }
    }

    private inner class MultiRecyclerViewAdapter(list: ArrayList<Item>) :
            MultiChoiceRecyclerViewAdapter<Item, MultiRecyclerViewAdapter.ViewHolder>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_multi_selection, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)

            holder.itemView.cbText.isChecked = isSelected(position)
            holder.itemView.cbText.text = item.name
        }

        internal inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root),
                CompoundButton.OnCheckedChangeListener {
            init {
                itemView.cbText.setOnCheckedChangeListener(this)
            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                // don't notify on checked change
                setSelected(adapterPosition, isChecked, false)
            }
        }
    }

    private inner class SingleRecyclerViewAdapter(list: ArrayList<Item>) :
            SingleChoiceRecyclerViewAdapter<Item, SingleRecyclerViewAdapter.ViewHolder>(list) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.row_single_selection, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = getItem(position)

            holder.itemView.rbText.isChecked = getSelectedItemPosition() == position
            holder.itemView.rbText.text = item.name
        }

        internal inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {
            init {
                itemView.rbText.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                setSelected(adapterPosition, true)
            }
        }
    }
}
