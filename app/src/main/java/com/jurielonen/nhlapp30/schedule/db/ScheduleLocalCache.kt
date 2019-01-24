package com.jurielonen.nhlapp30.schedule.db

import android.util.Log
import androidx.lifecycle.LiveData
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


    fun reposByDate(date: String): LiveData<List<Games>> {
        return scheduleDao.reposByDate(date)
    }

    fun checkByDate(date: String): List<Games> {
        return scheduleDao.checkByDate(date)
    }

    fun deleteByDate(date: String){
        ioExecutor.execute {
            Log.d("ScheduleLocalCache", "deleting $date repos")
            scheduleDao.deleteByDate(date)
        }
    }
}