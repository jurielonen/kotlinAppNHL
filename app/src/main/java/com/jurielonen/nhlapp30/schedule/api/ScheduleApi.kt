package com.jurielonen.nhlapp30.schedule.api

import com.jurielonen.nhlapp30.Helper
import com.jurielonen.nhlapp30.schedule.fragments.model.*
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.finalStats
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.previewRanks
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.list.previewStats
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
import java.util.concurrent.Executors


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
                    }else
                        onError("No games error")
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
                            Helper.parsePlays(plays, response.body()!!.gameData.teams!!.home!!.name!!),
                            Helper.parseTeamsPlayers(boxScore),
                            Helper.parseStats(response.body()!!.liveData.boxscore.teams.home.teamStats.teamSkaterStats!!,
                                                response.body()!!.liveData.boxscore.teams.away.teamStats.teamSkaterStats!!)
                        )
                    )

                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

fun searchPreviewData(api: ScheduleApi,
                      teams: String,
                      onSuccess: (schedule: GamePreviewData)->Unit,
                      onError: (error: String)-> Unit){
    api.searchPreviewData(teams, "team.stats", "points,goals,assists,shots,hits").enqueue(
        object : Callback<GamePreviewResponse>{
            override fun onFailure(call: Call<GamePreviewResponse>, t: Throwable) {
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<GamePreviewResponse>,
                response: Response<GamePreviewResponse>) {

                if(response.isSuccessful){

                    onSuccess(GamePreviewData(Helper.parsePreviewStats(
                        response.body()!!.teams[0].teamStats[0].splits[0].stat,
                        response.body()!!.teams[1].teamStats[0].splits[0].stat,
                        previewStats),
                        Helper.parsePreviewStats(
                            response.body()!!.teams[0].teamStats[0].splits[1].stat,
                            response.body()!!.teams[1].teamStats[0].splits[1].stat,
                            previewRanks),
                        Helper.parseTeamLeaders(response.body()!!.teams[0].teamLeaders, response.body()!!.teams[1].teamLeaders)
                        ))

                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}
interface ScheduleApi {

    @GET("api/v1/schedule")
    fun searchScheduleSingle(@Query("date") query: String,
                    @Query("hydrate") hydrate: String
                    ): Call<ScheduleResponse>


    @GET("api/v1/game/{id}/feed/live")
    fun searchGameData(@Path("id") id: String): Call<GameResponse>

    @GET("api/v1/teams")
    fun searchPreviewData(@Query("teamId") teams: String,
                          @Query("expand") stats: String,
                          @Query("leaderCategories") category: String): Call<GamePreviewResponse>
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
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ScheduleApi::class.java)
        }
    }
}