package com.jurielonen.nhlapp30.schedule.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: GameData)

    @Query("SELECT * FROM single_game_data WHERE (gamePK LIKE :query)")
    fun reposByID(query: String): DataSource.Factory<Int, GameData>

    @Query("DELETE FROM single_game_data WHERE (gamePK LIKE :query)")
    fun deleteByID(query: String)
}