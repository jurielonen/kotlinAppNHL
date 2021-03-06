package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

class GameSearchResult (
    val data: LiveData<GameData>,
    val networkErrors: LiveData<String>,
    val isInProgress: LiveData<Boolean>
    )