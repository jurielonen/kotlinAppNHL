package com.jurielonen.nhlapp30.schedule.api

import com.jurielonen.nhlapp30.schedule.fragments.model.*
import com.jurielonen.nhlapp30.schedule.model.Status
import com.jurielonen.nhlapp30.schedule.model.Venue

class GameResponse(val gamePk: Int,
                   val gameData: ResponseGameDetails,
                   val liveData: GameResponseLiveData)

class ResponseGameDetails(
    val status: Status? = Status(),
    val datetime: ResponseGameDateTime? = ResponseGameDateTime(),
    val venue: Venue? = Venue(),
    val teams: ResponseGameDataTeams? = ResponseGameDataTeams()
)

class ResponseGameDataTeams(
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

class GameResponseLiveData(
    val plays: ResponsePlays,
    val boxscore: ResponseBoxscore,
    val linescore: ResponseLinescore
)

class ResponseLinescore(
    val teams: ResponseLinescoreTeams
)

class ResponseLinescoreTeams(
    val home: ResponseLinescoreTeam,
    val away: ResponseLinescoreTeam
)

class ResponseLinescoreTeam(
    val goals: Int
)

class ResponsePlays(
    val allPlays: List<ResponseAllPlays>,
    val scoringPlays: List<Int>,
    val penaltyPlays: List<Int>
)

class ResponseAllPlays(
    val result: ResponseAllPlaysResult,
    val about: ResponseAllPlaysAbout,
    val team: ResponseAllPlaysTeam
)

class ResponseAllPlaysTeam(
    val name: String
)

class ResponseAllPlaysResult(
    val event: String,
    val description: String,
    val penaltyMinutes: Int? = 0
)

class ResponseAllPlaysAbout(
    val period: Int,
    val periodTime: String
)

class ResponseBoxscore(
    val teams: ResponseTeams
)

class ResponseTeams(
    val home: ResponseTeam,
    val away: ResponseTeam
)

class ResponseTeam(
    val teamStats: GameTeamStats,
    val team: GameDataTeam,
    val players: Map<String, ResponsePlayers>,
    val goalies: List<Int>,
    val skaters: List<Int>
)

class ResponsePlayers(
    val person: ResponsePerson,
    val position: ResponsePosition,
    val stats: ResponsePlayerStats
)

class ResponsePerson(
    val id: Int,
    val fullName: String
)

class ResponsePosition(
    val code: String,
    val name: String
)

class ResponsePlayerStats(
    val goalieStats: GameGoalieStats?,
    val skaterStats: GameSkaterStats?
)
