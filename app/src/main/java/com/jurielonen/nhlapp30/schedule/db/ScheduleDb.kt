package com.jurielonen.nhlapp30.schedule.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jurielonen.nhlapp30.schedule.model.Games


@Database(
    entities = [Games::class],
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
                ScheduleDb::class.java, "Github.db")
                .build()
    }
}
