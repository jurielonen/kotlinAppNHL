package com.jurielonen.nhlapp30.schedule.db

import android.util.Log
import androidx.paging.DataSource
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import java.util.concurrent.Executor

class GameLocalCache(private val gameDao: GameDao, private val ioExecutor: Executor) {

    fun insert(repos: GameData, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Log.d("GameLocalCache", "inserting ${repos.gamePk} repos")
            gameDao.insert(repos)
            insertFinished()
        }
    }


    fun reposById(id: String): DataSource.Factory<Int, GameData> {
        return gameDao.reposByID(id)
    }

    fun deleteById(id: String){
        ioExecutor.execute {
            Log.d("GameLocalCache", "deleting $id repos")
            gameDao.deleteByID(id)
        }
    }
}