package com.pointlessapps.kupmi.adapters

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import com.pointlessapps.kupmi.R
import com.pointlessapps.kupmi.models.Item
import org.jetbrains.anko.find

class AdapterItem : AdapterSimple<Item>(mutableListOf()) {

    init {
        setHasStableIds(true)
    }

    override fun getLayoutId(viewType: Int) = when (viewType) {
        +ItemType.SIMPLE -> R.layout.list_item_item
        else -> R.layout.list_item_header
    }

    override fun onBind(root: View, position: Int) {
        if (getItemViewType(position) != +ItemType.SIMPLE) {
            return
        }

        val item =
            list[if (position >= list.partition(Item::done).second.size) position - 1 else position]
        root.find<CheckBox>(R.id.itemText).apply {
            text = item.text

            paintFlags =
                if (item.done) {
                    paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }

            isChecked = item.done

            setOnCheckedChangeListener { _, checked ->
                item.done = checked
                paintFlags =
                    if (item.done) {
                        paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    } else {
                        paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    }

                post {
                    list.sort()
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount() = list.size + if (list.none(Item::done)) 0 else 1
    override fun getItemViewType(position: Int) = when (position) {
        list.partition(Item::done).second.size -> +ItemType.HEADER
        else -> +ItemType.SIMPLE
    }

    override fun getItemId(position: Int) = when {
        position == list.partition(Item::done).second.size -> 1000
        position >= list.partition(Item::done).second.size -> list[position - 1].hashCode()
        else -> list[position].hashCode()
    }.toLong()

    fun add(item: Item) = update(
        listOf(
            item,
            *list.toTypedArray()
        )
    )

    enum class ItemType(private val i: Int) {
        SIMPLE(1), HEADER(2);

        operator fun unaryPlus() = i
    }
}
