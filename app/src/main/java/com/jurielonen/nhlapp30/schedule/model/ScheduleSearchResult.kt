package com.jurielonen.nhlapp30.schedule.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class ScheduleSearchResult(
    val data: LiveData<PagedList<Games>>,
    val networkErrors: LiveData<String>
)