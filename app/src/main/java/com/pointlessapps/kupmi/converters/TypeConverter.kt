package com.pointlessapps.kupmi.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pointlessapps.kupmi.utils.fromJson
import java.util.*

class TypeConverter {

    @TypeConverter
    fun fromDate(input: Date?): String = Gson().toJson(input)

    @TypeConverter
    fun toDate(input: String): Date? = Gson().fromJson<Date>(input)
}
