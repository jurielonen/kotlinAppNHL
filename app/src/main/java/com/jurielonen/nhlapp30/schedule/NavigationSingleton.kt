package com.jurielonen.nhlapp30.schedule

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.model.Games

class NavigationSingleton {

    private lateinit var navController: NavController

    companion object {
        @Volatile
        private var INSTANCE: NavigationSingleton? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: NavigationSingleton().also {
                        INSTANCE = it
                    }
            }
    }

    fun setNavController(nav: NavController){
        navController = nav
    }

    fun statsClicked(game: Games){
        val args = Bundle()
        when(game.status.codedGameState){

            1->{
                args.putInt("SELECTED_GAME", game.gamePk)
                args.putInt("HOME_ID", game.teams.home.details.id)
                args.putInt("AWAY_ID", game.teams.away.details.id)
                args.putString("TIME", game.gameDate)
                args.putString("HOME_TEAM", game.teams.home.details.name)
                args.putString("AWAY_TEAM", game.teams.away.details.name)
                args.putString("HOME_RECORD", game.homeStat())
                args.putString("AWAY_RECORD", game.awayStat())
                args.putString("STATE", game.status.detailedState)
                Log.d("Navigation", "game preview")
                navController.navigate(R.id.action_schedule_dest_to_game_preview_dest, args)
            }

            3->{
                args.putInt("SELECTED_GAME", game.gamePk)
                args.putString("TIME", game.gameDate)
                args.putString("HOME_TEAM", game.teams.home.details.name)
                args.putString("AWAY_TEAM", game.teams.away.details.name)
                args.putString("HOME_RECORD", game.homeStat())
                args.putString("AWAY_RECORD", game.awayStat())
                args.putString("STATE", game.status.detailedState)
                Log.d("Navigation", "game live")
                //TODO: Check how state live works in final fragment
                navController.navigate(R.id.action_schedule_dest_to_game_final_dest, args)
            }
            7->{
                args.putInt("SELECTED_GAME", game.gamePk)
                args.putString("TIME", game.gameDate)
                args.putString("HOME_TEAM", game.teams.home.details.name)
                args.putString("AWAY_TEAM", game.teams.away.details.name)
                args.putString("HOME_RECORD", game.homeStat())
                args.putString("AWAY_RECORD", game.awayStat())
                args.putString("STATE", game.status.detailedState)
                args.putString("SELECTED_RECAP", game.urlRecap)
                args.putString("SELECTED_EXTENDED", game.urlExtended)
                Log.d("Navigation", "game final")
                navController.navigate(R.id.action_schedule_dest_to_game_final_dest, args)
            }
            else->{
                Log.d("Navigation", "View not made yet")
            }
        }
    }

    fun scheduleVideoButtonClicked(url: String){
        val args = Bundle()
        args.putString("URL", url)
        navController.navigate(R.id.action_schedule_dest_to_video_dest, args)
    }


    fun finalVideoButtonClicked(url: String){
        val args = Bundle()
        args.putString("URL", url)
        navController.navigate(R.id.action_game_final_dest_to_video_dest, args)
    }

}