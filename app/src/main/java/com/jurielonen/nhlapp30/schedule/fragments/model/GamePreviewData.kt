package com.jurielonen.nhlapp30.schedule.fragments.model

import com.jurielonen.nhlapp30.schedule.api.PreviewResponseLeaders

data class GamePreviewData(
    val teamStats: List<Stats> = emptyList(),
    val teamRanks: List<Stats> = emptyList(),
    val teamLeaders: List<PreviewResponseLeaders> = emptyList()
)