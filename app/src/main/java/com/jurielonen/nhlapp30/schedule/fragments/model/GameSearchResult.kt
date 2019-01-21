package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

class GameSearchResult (
    val data: LiveData<PagedList<GameData>>,
    val networkErrors: LiveData<String>
    )