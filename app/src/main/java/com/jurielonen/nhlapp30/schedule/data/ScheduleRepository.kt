package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.api.searchSchedule
import com.jurielonen.nhlapp30.schedule.db.ScheduleLocalCache
import com.jurielonen.nhlapp30.schedule.model.Dates
import com.jurielonen.nhlapp30.schedule.model.LoadingEnum
import com.jurielonen.nhlapp30.schedule.model.ScheduleSearchResult
import java.util.concurrent.Executor

class ScheduleRepository(
    private val api: ScheduleApi,
    private val cache: ScheduleLocalCache,
    private val executor: Executor)
{
    fun search(query: String): ScheduleSearchResult {
        Log.d("GithubRepository", "New query: $query")

        val isRequestInProgress = MutableLiveData<LoadingEnum>()
        isRequestInProgress.postValue(LoadingEnum.LOADING)

        val networkErrors = MutableLiveData<String>()

        val data = cache.reposByDate(query)

        executor.execute {
            val date = cache.checkByDate(query)
            if (date != null) {
                if (date.isThereGames) {
                    isRequestInProgress.postValue(LoadingEnum.SHOW_DATA)
                } else {
                    isRequestInProgress.postValue(LoadingEnum.SHOW_EMPTY)
                }
            } else {
                searchSchedule(api, query, { schedule ->
                    cache.insert(schedule) {
                        isRequestInProgress.postValue(LoadingEnum.LOADED)
                    }
                    cache.insertDates(Dates(query, true))
                }, {
                   cache.insertDates(it)
                    isRequestInProgress.postValue(LoadingEnum.SHOW_EMPTY)
                }, { error ->
                    networkErrors.postValue(error)
                    isRequestInProgress.postValue(LoadingEnum.ERROR)
                })
            }
        }

        return ScheduleSearchResult(data, networkErrors, isRequestInProgress)
    }

    fun refresh(query: String): ScheduleSearchResult{
        Log.d("GithubRepository", "Refresh: $query")

        val isRequestInProgress = MutableLiveData<LoadingEnum>()
        isRequestInProgress.postValue(LoadingEnum.REFRESH)
        val networkErrors = MutableLiveData<String>()

        cache.deleteByDate(query)

        val data = cache.reposByDate(query)


        searchSchedule(api, query, { schedule ->
            cache.insert(schedule) {
                isRequestInProgress.postValue(LoadingEnum.LOADED)
            }
            cache.insertDates(Dates(query, true))
        }, {
            cache.insertDates(it)
            isRequestInProgress.postValue(LoadingEnum.SHOW_EMPTY)
        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress.postValue(LoadingEnum.ERROR)
        })

        return ScheduleSearchResult(data, networkErrors, isRequestInProgress)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 5
    }
}
