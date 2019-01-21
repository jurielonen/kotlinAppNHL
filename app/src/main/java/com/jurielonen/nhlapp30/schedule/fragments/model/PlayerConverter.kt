package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class PlayerConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<GamePlayer>? {
        if(data == null)
            return Collections.emptyList()

        val listType = object : TypeToken<ArrayList<GamePlayer>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<GamePlayer>?): String? {
        val type = object : TypeToken<List<GamePlayer>>(){}.type
        return gson.toJson(someObjects, type)
    }
}