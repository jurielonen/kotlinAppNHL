package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.api.searchSchedule
import com.jurielonen.nhlapp30.schedule.db.ScheduleLocalCache
import com.jurielonen.nhlapp30.schedule.model.ScheduleSearchResult
import java.util.concurrent.Executor

class ScheduleRepository(
    private val api: ScheduleApi,
    private val cache: ScheduleLocalCache,
    private val executor: Executor)
{
    fun search(query: String): ScheduleSearchResult {
        Log.d("GithubRepository", "New query: $query")

        val isRequestInProgress = MutableLiveData<Boolean>()
        isRequestInProgress.postValue(true)
        val networkErrors = MutableLiveData<String>()
        val data = cache.reposByDate(query)

        executor.execute {
            val list = cache.checkByDate(query)
            val scheduleExists = list.isNotEmpty()
            if (!scheduleExists) {
                searchSchedule(api, query, { schedule ->
                    cache.insert(schedule) {
                        isRequestInProgress.postValue(false)
                    }
                }, { error ->
                    networkErrors.postValue(error)
                    isRequestInProgress.postValue(false)
                })
            } else {
                isRequestInProgress.postValue(false)
            }
        }
        return ScheduleSearchResult(data, networkErrors, isRequestInProgress)
    }

    fun refresh(query: String): ScheduleSearchResult{
        Log.d("GithubRepository", "Refresh: $query")

        val isRequestInProgress = MutableLiveData<Boolean>()
        isRequestInProgress.postValue(true)
        val networkErrors = MutableLiveData<String>()

        cache.deleteByDate(query)

        val data = cache.reposByDate(query)


        searchSchedule(api, query, { schedule ->
            cache.insert(schedule) {
                isRequestInProgress.postValue(false)
            }
        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress.postValue(false)
        })

        return ScheduleSearchResult(data, networkErrors, isRequestInProgress)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 5
    }
}
