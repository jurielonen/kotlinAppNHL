package com.jurielonen.nhlapp30.schedule

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.model.Games

class NavigationSingleton {

    private lateinit var navController: NavController
    private lateinit var mContext: Context

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

    fun setNavController(nav: NavController, mContext: Context){
        navController = nav
        this.mContext = mContext
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
                Toast.makeText(mContext, "This view is not yet made:(", Toast.LENGTH_SHORT).show()
            }

            3->{
                args.putInt("SELECTED_GAME", game.gamePk)
                args.putString("TIME", game.gameDate)
                args.putString("HOME_TEAM", game.teams.home.details.name)
                args.putString("AWAY_TEAM", game.teams.away.details.name)
                args.putString("HOME_RECORD", game.homeStat())
                args.putString("AWAY_RECORD", game.awayStat())
                args.putString("STATE", game.status.detailedState)
                Toast.makeText(mContext, "This view is not yet made:(", Toast.LENGTH_SHORT).show()
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
                navController.navigate(R.id.action_schedule_dest_to_game_final_dest, args)
            }
            else->{
                Toast.makeText(mContext, "This view is not yet made:(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}