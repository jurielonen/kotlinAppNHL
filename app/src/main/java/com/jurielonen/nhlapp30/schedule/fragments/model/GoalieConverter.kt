package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GoalieConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String): List<GameGoalie> {

        val listType = object : TypeToken<ArrayList<GameGoalie>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<GameGoalie>?): String? {
        val listType = object : TypeToken<ArrayList<GameGoalie>>() {}.type
        return gson.toJson(someObjects, listType)
    }
}