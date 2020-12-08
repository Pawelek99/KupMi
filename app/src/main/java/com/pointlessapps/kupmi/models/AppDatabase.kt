package com.pointlessapps.kupmi.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pointlessapps.kupmi.converters.TypeConverter
import com.pointlessapps.kupmi.daos.ItemDao
import com.pointlessapps.kupmi.utils.Utils

@Database(entities = [Item::class], version = 1)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object : Utils.SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(
            it!!,
            AppDatabase::class.java,
            "kupMiDB"
        ).fallbackToDestructiveMigration().build()
    })
}
