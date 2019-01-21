package com.jurielonen.nhlapp30.schedule.fragments.model

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.jurielonen.nhlapp30.schedule.model.Games
import com.jurielonen.nhlapp30.schedule.model.Status
import com.jurielonen.nhlapp30.schedule.model.Venue


@TypeConverters(PlayerConverter::class, GoalieConverter::class, PlayConverter::class)
@Entity(tableName = "single_game_data",
    foreignKeys = [ForeignKey(
        entity = Games::class,
        parentColumns = arrayOf("gamePk"),
        childColumns = arrayOf("gamePk"))]
)
data class GameData(
    @PrimaryKey
    val gamePk: Int,
    @Embedded
    val gameDetails: GameDetails? = GameDetails(),
    val plays: List<GamePlays>? = emptyList(),
    @Embedded
    val boxScore: GameBoxScore? = GameBoxScore()
)

data class GameDetails(
    @Embedded
    val status: Status? = Status(),
    @Embedded
    val datetime: GameDateTime? = GameDateTime(),
    @Embedded
    val venue: Venue? = Venue(),
    @Embedded
    val teams: GameDataTeams? = GameDataTeams()
)

data class GameDateTime(
    val dateTime: String? = ""
)

data class GameDataTeams(
    @Embedded(prefix = "home_")
    val home: GameDataTeam? = GameDataTeam(),
    @Embedded(prefix = "away_")
    val away: GameDataTeam? = GameDataTeam()
)

@Entity
data class GamePlays(
    val event: String,
    val description: String,
    val penaltyMinutes: Int? = 0,
    val period: Int,
    val periodTime: String
)

data class GameBoxScore(
    @Embedded(prefix = "teams_box_")
    val teams: BoxScoreTeams? = BoxScoreTeams()
)

data class BoxScoreTeams(
    @Embedded(prefix = "home_box_")
    val home: BoxScoreTeamHome? = BoxScoreTeamHome(),
    @Embedded(prefix = "away_box_")
    val away: BoxScoreTeamAway? = BoxScoreTeamAway()
)


data class BoxScoreTeamHome(
    @Embedded
    val team: GameDataTeam? = GameDataTeam(),
    @Embedded
    val teamStats: GameTeamStats? = GameTeamStats(),
    val goalies: List<GameGoalie>? = emptyList(),
    val skaters: List<GamePlayer>? = emptyList()
)

data class BoxScoreTeamAway(
    @Embedded
    val team: GameDataTeam? = GameDataTeam(),
    @Embedded
    val teamStats: GameTeamStats? = GameTeamStats(),
    val goalies: List<GameGoalie>? = emptyList(),
    val skaters: List<GamePlayer>? = emptyList()
)

data class GameDataTeam(
    val id: Int? = 0,
    val name: String? = ""
)

@Entity
data class GameGoalie(
    val id: Int? = 0,
    val fullName: String? = "",
    val code: String? = "",
    val name: String? = "",
    val stats: GameGoalieStats? = GameGoalieStats()
)

@Entity
data class GamePlayer(
    val id: Int? = 0,
    val fullName: String? = "",
    val code: String? = "",
    val name: String? = "",
    val stats: GameSkaterStats? = GameSkaterStats()
)

data class GameTeamStats(
    @Embedded
    val teamSkaterStats: GameTeamSkaterStats? = GameTeamSkaterStats()
)

data class GameTeamSkaterStats(
    val goals: Int = 0,
    val pi: Int = 0,
    val shots: Int = 0,
    val powerPlayPercentage: String? = "",
    val powerPlayGoals: Int = 0,
    val powerPlayOpportunities: Int = 0,
    val faceOffWinPercentage: String? = "",
    val blocked: Int = 0,
    val takeaways: Int = 0,
    val giveaways: Int = 0,
    val hits: Int = 0
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