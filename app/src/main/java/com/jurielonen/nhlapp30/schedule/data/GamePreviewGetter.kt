package com.jurielonen.nhlapp30.schedule.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jurielonen.nhlapp30.schedule.api.ScheduleApi
import com.jurielonen.nhlapp30.schedule.api.searchPreviewData
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePreviewData
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePreviewSearchResult

class GamePreviewGetter(private val api: ScheduleApi)
{
    fun search(home: Int, away: Int): GamePreviewSearchResult {
        Log.d("GameRepository", "New query: $home,$away")

        val isRequestInProgress = MutableLiveData<Boolean>()
        isRequestInProgress.postValue(true)

        val networkErrors = MutableLiveData<String>()

        val data = MutableLiveData<GamePreviewData>()

        searchPreviewData(api, home, away, { game ->
            isRequestInProgress.postValue(false)
            data.postValue(game)
        }, { error ->
            isRequestInProgress.postValue(false)
            networkErrors.postValue(error)
        })

        return GamePreviewSearchResult(data, networkErrors, isRequestInProgress)
    }
}