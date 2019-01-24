package com.jurielonen.nhlapp30.schedule.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class ScheduleSearchResult(
    val data: LiveData<List<Games>>,
    val networkErrors: LiveData<String>,
    val isInProgress: LiveData<LoadingEnum>
)

enum class LoadingEnum{
    SHOW_DATA, SHOW_EMPTY, LOADED, LOADING, REFRESH, ERROR
}