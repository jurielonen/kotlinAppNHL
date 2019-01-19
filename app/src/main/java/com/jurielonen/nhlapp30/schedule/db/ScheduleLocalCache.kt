package com.jurielonen.nhlapp30.schedule.db

import android.util.Log
import androidx.paging.DataSource
import com.jurielonen.nhlapp30.schedule.model.Games
import java.util.concurrent.Executor

class ScheduleLocalCache(private val scheduleDao: ScheduleDao, private val ioExecutor: Executor) {

    fun insert(repos: List<Games>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("ScheduleLocalCache", "inserting ${repos.size} repos")
            scheduleDao.insert(repos)
            insertFinished()
        }
    }

    fun reposByDate(date: String): DataSource.Factory<Int, Games> {
        // appending '%' so we can allow other characters to be before and after the query string
        //val query = "%${date.replace(' ', '%')}%"
        return scheduleDao.reposByDate(date)
    }
}