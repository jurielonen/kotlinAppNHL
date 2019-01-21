package com.jurielonen.nhlapp30.schedule.api

import com.jurielonen.nhlapp30.schedule.fragments.model.*
import org.json.JSONObject

class GameResponse(val gamePk: Int,
                   val gameData: GameDetails,
                   val liveData: GameResponseLiveData)

class GameResponseLiveData(
    val plays: ResponsePlays,
    val boxscore: ResponseBoxscore
)

class ResponsePlays(
    val allPlays: List<ResponseAllPlays>,
    val scoringPlays: List<Int>,
    val penaltyPlays: List<Int>
)

class ResponseAllPlays(
    val result: ResponseAllPlaysResult,
    val about: ResponseAllPlaysAbout
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
