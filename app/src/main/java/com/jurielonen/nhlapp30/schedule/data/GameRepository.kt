package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.db.GameLocalCache
import com.jurielonen.nhlapp30.schedule.fragments.model.GameSearchResult

class GameRepository(private val api: ScheduleApi,
                     private val cache: GameLocalCache
)
{
    fun search(query: String): GameSearchResult {
        Log.d("GithubRepository", "New query: $query")


        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposById(query)

        // Construct the boundary callback
        val boundaryCallback = GameBoundaryCallBack(query, api, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()


        // Get the network errors exposed by the boundary callback
        return GameSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}