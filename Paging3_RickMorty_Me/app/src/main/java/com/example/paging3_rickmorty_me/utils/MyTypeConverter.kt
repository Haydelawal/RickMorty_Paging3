package com.example.paging3_rickmorty_me.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyTypeConverter {

    @TypeConverter
    fun fromListOftring(value: String): List<String> {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toListOfString(list: List<String>): String {
        return Gson().toJson(list)
    }
}