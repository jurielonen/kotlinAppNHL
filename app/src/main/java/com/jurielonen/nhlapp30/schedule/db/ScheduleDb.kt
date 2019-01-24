package com.jurielonen.nhlapp30.schedule.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import com.jurielonen.nhlapp30.schedule.fragments.model.GoalieConverter
import com.jurielonen.nhlapp30.schedule.fragments.model.PlayerConverter
import com.jurielonen.nhlapp30.schedule.model.Dates
import com.jurielonen.nhlapp30.schedule.model.Games


@Database(
    entities = [Games::class, Dates::class],
    version = 1,
    exportSchema = false
)
abstract class ScheduleDb: RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: ScheduleDb? = null

        fun getInstance(context: Context?): ScheduleDb =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context?) =
            Room.databaseBuilder(
                context!!.applicationContext,
                ScheduleDb::class.java, "NHL.db")
                .build()
    }
}
