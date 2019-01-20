package com.jurielonen.nhlapp30.schedule

import android.os.Bundle
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
                args.putInt("HOME_ID", game.teams.home.details.id)
                args.putInt("AWAY_ID", game.teams.away.details.id)
                args.putString("HOME_RECORD", game.homeRecord())
                args.putString("AWAY_RECORD", game.awayRecord())
            }

            3->{
                args.putInt("SELECTED_GAME", game.gamePk)
            }
            7->{
                args.putInt("SELECTED_GAME", game.gamePk)
                args.putString("SELECTED_RECAP", game.urlRecap)
                args.putString("SELECTED_EXTENDED", game.urlExtended)
            }
            else->{

            }
        }
        navController.navigate(R.id.action_schedule_dest_to_game_final_dest, args)
    }
}