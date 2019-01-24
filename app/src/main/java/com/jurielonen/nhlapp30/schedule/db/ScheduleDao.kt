package com.jurielonen.nhlapp30.schedule.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jurielonen.nhlapp30.schedule.model.Games

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Games>)

    @Query("SELECT * FROM games WHERE (date LIKE :query)")
    fun reposByDate(query: String): LiveData<List<Games>>

    @Query("SELECT * FROM games WHERE (date LIKE :query)")
    fun checkByDate(query: String): List<Games>

    @Query("DELETE FROM games WHERE (date LIKE :query)")
    fun deleteByDate(query: String)
}