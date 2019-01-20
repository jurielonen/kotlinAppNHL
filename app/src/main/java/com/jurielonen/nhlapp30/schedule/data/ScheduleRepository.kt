package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.db.ScheduleLocalCache
import com.jurielonen.nhlapp30.schedule.model.ScheduleSearchResult

class ScheduleRepository(
    private val api: ScheduleApi,
    private val cache: ScheduleLocalCache)
{
    fun search(query: String): ScheduleSearchResult {
        Log.d("GithubRepository", "New query: $query")


        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByDate(query)

        // Construct the boundary callback
        val boundaryCallback = ScheduleBoundaryCallback(query, api, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()


        // Get the network errors exposed by the boundary callback
        return ScheduleSearchResult(data, networkErrors)
    }

    fun refresh(query: String): ScheduleSearchResult{
        Log.d("GithubRepository", "Refresh: $query")
        cache.deleteByDate(query)

        // Get data source factory from the local cache
        val dataSourceFactory = cache.reposByDate(query)

        // Construct the boundary callback
        val boundaryCallback = ScheduleBoundaryCallback(query, api, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()


        // Get the network errors exposed by the boundary callback
        return ScheduleSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}
