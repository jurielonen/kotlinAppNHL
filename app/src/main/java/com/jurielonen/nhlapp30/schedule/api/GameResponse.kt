package com.jurielonen.nhlapp30.schedule.api

import com.jurielonen.nhlapp30.schedule.fragments.model.*
import com.jurielonen.nhlapp30.schedule.model.Status
import com.jurielonen.nhlapp30.schedule.model.Venue

data class GameResponse(val gamePk: Int,
                   val gameData: ResponseGameDetails,
                   val liveData: GameResponseLiveData)

data class ResponseGameDetails(
    val status: Status? = Status(),
    val datetime: ResponseGameDateTime? = ResponseGameDateTime(),
    val venue: Venue? = Venue(),
    val teams: ResponseGameDataTeams? = ResponseGameDataTeams()
)

data class ResponseGameDataTeams(
    val home: ResponseGameDataTeam? = ResponseGameDataTeam(),
    val away: ResponseGameDataTeam? = ResponseGameDataTeam()
)

data class ResponseGameDataTeam(
    val id: Int? = 0,
    val name: String? = ""
)

data class ResponseGameDateTime(
    val dateTime: String? = ""
)

data class GameResponseLiveData(
    val plays: ResponsePlays,
    val boxscore: ResponseBoxscore,
    val linescore: ResponseLinescore
)

data class ResponseLinescore(
    val teams: ResponseLinescoreTeams
)

data class ResponseLinescoreTeams(
    val home: ResponseLinescoreTeam,
    val away: ResponseLinescoreTeam
)

data class ResponseLinescoreTeam(
    val goals: Int
)

data class ResponsePlays(
    val allPlays: List<ResponseAllPlays>,
    val scoringPlays: List<Int>,
    val penaltyPlays: List<Int>
)

data class ResponseAllPlays(
    val result: ResponseAllPlaysResult,
    val about: ResponseAllPlaysAbout,
    val team: ResponseAllPlaysTeam
)

data class ResponseAllPlaysTeam(
    val name: String
)

data class ResponseAllPlaysResult(
    val event: String,
    val description: String,
    val penaltyMinutes: Int? = 0
)

data class ResponseAllPlaysAbout(
    val period: Int,
    val periodTime: String
)

data class ResponseBoxscore(
    val teams: ResponseTeams
)

data class ResponseTeams(
    val home: ResponseTeam,
    val away: ResponseTeam
)

data class ResponseTeam(
    val teamStats: ResponseGameTeamStats,
    val team: GameDataTeam,
    val players: Map<String, ResponsePlayers>,
    val goalies: List<Int>,
    val skaters: List<Int>
)


data class ResponseGameTeamStats(
    val teamSkaterStats: ResponseGameTeamSkaterStats? = ResponseGameTeamSkaterStats()
)

data class ResponseGameTeamSkaterStats(
    val goals: Int = 0,
    val pim: Int = 0,
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

data class ResponsePlayers(
    val person: ResponsePerson,
    val position: ResponsePosition,
    val stats: ResponsePlayerStats
)

data class ResponsePerson(
    val id: Int,
    val fullName: String
)

data class ResponsePosition(
    val code: String,
    val name: String
)

data class ResponsePlayerStats(
    val goalieStats: GameGoalieStats?,
    val skaterStats: GameSkaterStats?
)
