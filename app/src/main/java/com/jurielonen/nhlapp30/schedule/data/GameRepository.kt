package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.api.searchGame
import com.jurielonen.nhlapp30.schedule.fragments.model.GameData
import com.jurielonen.nhlapp30.schedule.fragments.model.GameSearchResult

class GameRepository(private val api: ScheduleApi)
{
    fun search(query: String): GameSearchResult {
        Log.d("GameRepository", "New query: $query")

        val isRequestInProgress = MutableLiveData<Boolean>()
        isRequestInProgress.postValue(true)

        val networkErrors = MutableLiveData<String>()

        val data = MutableLiveData<GameData>()

        searchGame(api, query, { game ->
            isRequestInProgress.postValue(false)
            data.postValue(game)
        }, { error ->
            isRequestInProgress.postValue(false)
            networkErrors.postValue(error)
        })

        return GameSearchResult(data, networkErrors, isRequestInProgress)
    }
}