package com.pointlessapps.kupmi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AdapterSimple<T>(protected open val list: MutableList<T>) :
    RecyclerView.Adapter<AdapterSimple.DataObjectHolder>() {

    var onClickListener: ((T) -> Unit)? = null

    abstract fun getLayoutId(viewType: Int): Int
    abstract fun onBind(root: View, position: Int)

    open fun onCreate(root: View) = Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataObjectHolder {
        return DataObjectHolder(
            LayoutInflater.from(parent.context!!).inflate(getLayoutId(viewType), parent, false),
            ::onCreate
        )
    }

    override fun onBindViewHolder(holder: DataObjectHolder, position: Int) =
        onBind(holder.root, position)

    override fun getItemCount() = list.size

    override fun getItemId(position: Int) = when {
        !hasStableIds() -> RecyclerView.NO_ID
        position in 0 until list.size -> list[position].hashCode().toLong()
        else -> position.toLong()
    }

    open fun update(list: List<T>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    class DataObjectHolder(itemView: View, onCreateCallback: (View) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val root = itemView

        init {
            onCreateCallback.invoke(root)
        }
    }
}
