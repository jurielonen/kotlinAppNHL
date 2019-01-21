package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.api.searchGame
import com.jurielonen.nhlapp30.schedule.db.GameLocalCache
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData

class GameBoundaryCallBack (
    private val query: String,
    private val api: ScheduleApi,
    private val cache: GameLocalCache
) : PagedList.BoundaryCallback<GameData>() {

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    // LiveData of network errors.
    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors



    override fun onZeroItemsLoaded() {
        Log.d("ScheduleCallback", "onZeroItemsLoaded")
        requestAndSaveData(query)
    }

    /*
    override fun onItemAtEndLoaded(itemAtEnd: Games) {
        Log.d("ScheduleCallback", "onItemAtEndLoaded")
        requestAndSaveData(query)
    }*/

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        searchGame(api, query, { game ->
            cache.insert(game) {
                isRequestInProgress = false
            }
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}