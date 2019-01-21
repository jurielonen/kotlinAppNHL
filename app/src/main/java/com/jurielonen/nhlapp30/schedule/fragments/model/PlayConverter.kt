package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class PlayConverter {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(data: String?): List<GamePlays>? {

        if (data == null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<ArrayList<GamePlays>>() {}.type
        return gson.fromJson(data, listType)


    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<GamePlays>?): String? {
        val x = gson.toJson(someObjects)
        return x
    }
}