package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.api.searchPreviewData
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePreviewData
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePreviewSearchResult

class GamePreviewGetter(private val api: ScheduleApi)
{
    fun search(query: String): GamePreviewSearchResult {
        Log.d("GameRepository", "New query: $query")

        val isRequestInProgress = MutableLiveData<Boolean>()
        isRequestInProgress.postValue(true)

        val networkErrors = MutableLiveData<String>()

        val data = MutableLiveData<GamePreviewData>()

        searchPreviewData(api, query, { game ->
            isRequestInProgress.postValue(false)
            data.postValue(game)
        }, { error ->
            isRequestInProgress.postValue(false)
            networkErrors.postValue(error)
        })

        return GamePreviewSearchResult(data, networkErrors, isRequestInProgress)
    }
}