package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.lifecycle.LiveData

class GamePreviewSearchResult (
    val data: LiveData<GamePreviewData>,
    val networkErrors: LiveData<String>,
    val isInProgress: LiveData<Boolean>
    )