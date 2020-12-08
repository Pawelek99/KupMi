package com.pointlessapps.kupmi.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pointlessapps.kupmi.models.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM table_items")
    fun findAll(): List<Item>

    @Query("SELECT * FROM table_items WHERE text LIKE ('%' || :query || '%')")
    fun findByQuery(query: String): List<Item>

    @Insert
    fun insert(vararg items: Item)

    @Delete
    fun delete(vararg items: Item)
}
