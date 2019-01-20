package com.jurielonen.nhlapp30.schedule.api

import com.jurielonen.nhlapp30.schedule.model.Games
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


fun searchSchedule(api: ScheduleApi,
                   path: String,
                   onSuccess: (schedule: List<Games>)->Unit,
                   onError: (error: String)-> Unit){

    api.searchRepos(path, "game(content(media(epg),highlights(scoreboard)))").enqueue(
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

interface ScheduleApi {

    @GET("api/v1/schedule")
    fun searchRepos(@Query("date") query: String,
                    @Query("hydrate") hydrate: String
                    ): Call<ScheduleResponse>


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