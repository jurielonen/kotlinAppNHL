package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.google.gson.annotations.SerializedName
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.PlaysConstant
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.SkaterConstants
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.ViewType
import com.jurielonen.nhlapp30.schedule.model.Games
import com.jurielonen.nhlapp30.schedule.model.Status
import com.jurielonen.nhlapp30.schedule.model.Venue



data class GameData(
    val gamePk: Int,
    val status: Int,
    val dateTime: String,
    val venue: String,
    val state: String,
    val home: GameDataTeam? = GameDataTeam(),
    val away: GameDataTeam? = GameDataTeam(),
    val plays: List<ViewType> = emptyList(),
    val players: List<ViewType> = emptyList(),
    val stats: List<Stats> = emptyList()
)

data class GameDataTeam(
    val id: Int? = 0,
    val name: String? = "",
    val goals: Int = 0
)

data class GamePlays(
    val homeTeam: Boolean,
    val team: String,
    val event: String,
    val description: String,
    val penaltyMinutes: Int? = 0,
    val period: Int,
    val periodTime: String
): ViewType{
    override fun getViewType() = PlaysConstant.ITEM
    val time = (Integer.toString(period) + periodTime.replace(":", "")).toInt()
    val goal = event == "Goal"
}

data class GameGoalie(
    val id: Int? = 0,
    val fullName: String? = "",
    val code: String? = "",
    val name: String? = "",
    val stats: GameGoalieStats? = GameGoalieStats()
): ViewType{
    override fun getViewType() = SkaterConstants.GOALIE
}

data class GamePlayer(
    val id: Int? = 0,
    val fullName: String? = "",
    val code: String? = "",
    val name: String? = "",
    val stats: GameSkaterStats? = GameSkaterStats()
): ViewType{
    override fun getViewType() = SkaterConstants.PLAYER
}
data class Stats(
    val home: String,
    val away: String,
    val statName: String
)

class GameSkaterStats(
    val timeOnIce: String? = "",
    val assists: Int? = 0,
    val goals: Int? = 0,
    val shots: Int? = 0,
    val hits: Int? = 0,
    val powerPlayGoals: Int? = 0,
    val powerPlayAssists: Int? = 0,
    val penaltyMinutes: Int? = 0,
    val faceOffPct: String? = "",
    val faceOffWins: Int? = 0,
    val faceoffTaken: Int? = 0,
    val takeaways: Int? = 0,
    val giveaways: Int? = 0,
    val shortHandedGoals: Int? = 0,
    val shortHandedAssists: Int? = 0,
    val blocked: Int? = 0,
    val plusMinus: Int? = 0,
    val evenTimeOnIce: String? = "",
    val powerPlayTimeOnIce: String? = "",
    val shortHandedTimeOnIce: String? = ""
)

class GameGoalieStats(
    val timeOnIce: String? = "",
    val assists: Int? = 0,
    val goals: Int? = 0,
    val pim: Int? = 0,
    val shots: Int? = 0,
    val saves: Int? = 0,
    val powerPlaySaves: Int? = 0,
    val shortHandedSaves: Int? = 0,
    val evenSaves: Int? = 0,
    val shortHandedShotsAgainst: Int? = 0,
    val evenShotsAgainst: Int? = 0,
    val powerPlayShotsAgainst: Int? = 0,
    val decision: String? = "",
    val savePercentage: Double? = 0.0,
    val evenStrengthSavePercentage: Double? = 0.0
)