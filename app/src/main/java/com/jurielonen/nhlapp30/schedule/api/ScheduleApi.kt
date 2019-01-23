package com.jurielonen.nhlapp30.schedule.api

import android.util.Log
import com.google.gson.Gson
import com.jurielonen.nhlapp30.schedule.fragments.model.*
import com.jurielonen.nhlapp30.schedule.model.Games
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


fun searchSchedule(api: ScheduleApi,
                   path: String,
                   onSuccess: (schedule: List<Games>)->Unit,
                   onError: (error: String)-> Unit){

    api.searchScheduleSingle(path, "game(content(media(epg),highlights(scoreboard)))").enqueue(
        object : Callback<ScheduleResponse>{
            override fun onFailure(call: Call<ScheduleResponse>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<ScheduleResponse>,
                response: Response<ScheduleResponse>
            ) {
                if(response.isSuccessful){
                    if(!response.body()!!.dates.isEmpty()) {
                        val date = response.body()?.dates!![0].date

                        val urls = response.body()?.dates!![0].games.map{
                            var extended = ""
                            var recap = ""
                            if(it.content.media.epg[2].items!!.isNotEmpty()){
                                extended = it.content.media.epg[2].items!![0].playbacks!![9].url!!
                                recap = it.content.media.epg[3].items!![0].playbacks!![9].url!!
                            }
                            Urls(recap, extended)
                        }
                        val games = response.body()?.dates!![0].games.mapIndexed { index, games ->
                            val game = Games(games.gamePk, games.link, games.gameDate, games.status, games.teams, games.venue)
                            game.date = date
                            game.urlRecap = urls[index].recap
                            game.urlExtended = urls[index].extended
                            game
                        }

                        onSuccess(games)
                    }
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }

        }
    )
}


fun searchGame(api: ScheduleApi,
               path: String,
               onSuccess: (schedule: GameData)->Unit,
               onError: (error: String)-> Unit){


    api.searchGameData(path).enqueue(
        object : Callback<GameResponse>{
            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<GameResponse>,
                response: Response<GameResponse>) {

                if(response.isSuccessful){

                    val plays = response.body()!!.liveData.plays
                    val boxScore = response.body()!!.liveData.boxscore.teams

                    onSuccess(
                        GameData(
                            response.body()!!.gamePk,
                            response.body()!!.gameData.status!!.codedGameState!!,
                            response.body()!!.gameData.datetime!!.dateTime!!,
                            response.body()!!.gameData.venue!!.name!!,
                            response.body()!!.gameData.status!!.detailedState!!,
                            GameDataTeam(
                                response.body()!!.gameData.teams!!.home!!.id,
                                response.body()!!.gameData.teams!!.home!!.name,
                                response.body()!!.liveData.linescore.teams.home.goals),
                            GameDataTeam(
                                response.body()!!.gameData.teams!!.away!!.id,
                                response.body()!!.gameData.teams!!.away!!.name,
                                response.body()!!.liveData.linescore.teams.away.goals),
                            parsePlays(plays),
                            parseTeams(boxScore)
                        )
                    )

                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

private fun parsePlays(plays: ResponsePlays): List<GamePlays>{
    val allPlays = plays.allPlays
    val playsToGet = (plays.scoringPlays + plays.penaltyPlays)
    playsToGet.sortedDescending()
    return playsToGet.map {
        val x = allPlays[it]
        GamePlays(x.team.name ,x.result.event, x.result.description, x.result.penaltyMinutes, x.about.period, x.about.periodTime)
    }
}

private fun parseTeams(teams: ResponseTeams): GameBoxScore{

    return GameBoxScore(BoxScoreTeams(
        BoxScoreTeamHome(teams.home.team.id, teams.home.team.name, teams.home.teamStats, parseGoalies(teams.home), parsePlayers(teams.home)),
        BoxScoreTeamAway(teams.away.team.id, teams.away.team.name, teams.away.teamStats, parseGoalies(teams.away), parsePlayers(teams.away))))
}

private fun parsePlayers(team: ResponseTeam): List<GamePlayer> {
    val allPlayers = team.players
    return team.skaters.map {
        var x = GamePlayer()
        for (a in allPlayers) {
            if(a.key == "ID$it") {
                x = GamePlayer(a.value.person.id, a.value.person.fullName, a.value.position.code, a.value.position.name, a.value.stats.skaterStats)
                break
            }
        }
        x
    }
}

private fun parseGoalies(team: ResponseTeam): List<GameGoalie> {
    val allPlayers = team.players
    return team.goalies.map {
        var x = GameGoalie()
        for (a in allPlayers) {
            if (a.key == "ID$it") {
                x = GameGoalie(it, a.value.person.fullName, a.value.position.code, a.value.position.name, a.value.stats.goalieStats)
                break
            }
        }
        x
    }

}

interface ScheduleApi {

    @GET("api/v1/schedule")
    fun searchScheduleSingle(@Query("date") query: String,
                    @Query("hydrate") hydrate: String
                    ): Call<ScheduleResponse>


    @GET("api/v1/game/{id}/feed/live")
    fun searchGameData(@Path("id") id: String): Call<GameResponse>

    companion object {
        private const val BASE_URL = "https://statsapi.web.nhl.com/"

        fun create(): ScheduleApi {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ScheduleApi::class.java)
        }
    }
}