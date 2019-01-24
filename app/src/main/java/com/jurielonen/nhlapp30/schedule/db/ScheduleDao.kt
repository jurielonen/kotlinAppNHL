package com.jurielonen.nhlapp30.schedule.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jurielonen.nhlapp30.schedule.model.Dates
import com.jurielonen.nhlapp30.schedule.model.Games

@Dao
interface ScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Games>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(post: Dates)

    @Query("SELECT * FROM games WHERE (date LIKE :query)")
    fun reposByDate(query: String): LiveData<List<Games>>

    @Query("SELECT * FROM dates WHERE (date LIKE :query) LIMIT 1")
    fun checkByDate(query: String): Dates

    @Query("DELETE FROM games WHERE (date LIKE :query)")
    fun deleteByDate(query: String)
}