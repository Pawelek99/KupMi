package com.pointlessapps.kupmi.ui.lists

import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pointlessapps.kupmi.adapters.AdapterItem
import com.pointlessapps.kupmi.models.Item
import kotlinx.android.synthetic.main.fragment_lists.view.*

class ViewModelLists(private val root: ViewGroup) : ViewModel() {

    fun prepareListItems() {
        root.listItems.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = AdapterItem()
        }
    }

    fun prepareInput() {
        root.inputItem.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                (root.listItems.adapter as? AdapterItem)?.apply {
                    add(Item(root.inputItem.text.toString()))
                    notifyDataSetChanged()
                }

                root.inputItem.text?.clear()

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }
}
