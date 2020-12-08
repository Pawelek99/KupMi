package com.pointlessapps.kupmi.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "table_items")
data class Item(
    @ColumnInfo(name = "text", index = true)
    var text: String,
    @ColumnInfo(name = "done", index = true)
    var done: Boolean = false,
    @ColumnInfo(name = "created_at")
    val createdAt: Date = Date(),
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = UUID.randomUUID().hashCode().toLong(),
) : Comparable<Item> {
    override fun compareTo(other: Item) =
        compareValuesBy(this, other, { it.done }, { it.createdAt })
}
