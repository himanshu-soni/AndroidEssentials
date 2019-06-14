package me.himanshusoni.basicextensions

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

interface KotlinOnItemSelectedListener : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
    override fun onNothingSelected(parent: AdapterView<*>?) {}
}

fun Spinner.onItemSelected(action: (parent: AdapterView<*>?, view: View?, position: Int, id: Long) -> Unit) {
    val oldOsl = onItemSelectedListener
    onItemSelectedListener = object : KotlinOnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            action.invoke(parent, view, position, id)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            oldOsl?.onNothingSelected(parent)
        }
    }
}

fun Spinner.onNothingSelected(action: (parent: AdapterView<*>?) -> Unit) {
    val oldOsl = onItemSelectedListener
    onItemSelectedListener = object : KotlinOnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            oldOsl?.onItemSelected(parent, view, position, id)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            action.invoke(parent)
        }
    }
}