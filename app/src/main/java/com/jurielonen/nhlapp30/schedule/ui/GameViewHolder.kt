package com.jurielonen.nhlapp30.schedule.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.databinding.ScheduleGamesBinding
import com.jurielonen.nhlapp30.schedule.model.Games

class GameViewHolder(private val binding: ScheduleGamesBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Games) {
        with(binding) {
            gameData = game
            executePendingBindings()
        }
    }

}

/*

    private val home: TextView = view.findViewById(R.id.scheduleHomeNameText)
    private val away: TextView = view.findViewById(R.id.scheduleAwayNameText)
    private val homeImage: ImageView = view.findViewById(R.id.scheduleHomeLogoImage)
    private val awayImage: ImageView = view.findViewById(R.id.scheduleAwayLogoImage)
    private val state: TextView = view.findViewById(R.id.scheduleStateText)
    private val homeInfo: TextView = view.findViewById(R.id.homeInfoText)
    private val awayInfo: TextView = view.findViewById(R.id.awayInfoText)
    private val venue: TextView = view.findViewById(R.id.scheduleVenueText)
    private val time: TextView = view.findViewById(R.id.scheduleTimeText)
    private val statsButton: Button = view.findViewById(R.id.statsButton)
    private val recapButton: Button = view.findViewById(R.id.recapButton)
    private val extendedButton: Button = view.findViewById(R.id.extendedButton)

    private var games: Games? = null

    fun bind(games: Games) {
        if (games == null) {
            val resources = itemView.resources
            state.text = resources.getString(R.string.loading)
            homeImage.visibility = View.GONE
            awayImage.visibility = View.GONE
            home.visibility = View.GONE
            away.visibility = View.GONE
            homeInfo.visibility = View.GONE
            awayInfo.visibility = View.GONE
            venue.visibility = View.GONE
            time.visibility = View.GONE
            statsButton.visibility = View.GONE
            recapButton.visibility = View.GONE
            extendedButton.visibility = View.GONE
        } else {
            showRepoData(games)
        }
    }

    private fun showRepoData(games: Games) {
        this.games = games

        home.text = games.teams.home.details.name
        home.visibility = View.VISIBLE
        away.text = games.teams.away.details.name
        away.visibility = View.VISIBLE
        homeInfo.text = games.teams.home.score.toString()
        homeInfo.visibility = View.VISIBLE
        awayInfo.text = games.teams.away.score.toString()
        awayInfo.visibility = View.VISIBLE

    }

    companion object {
        fun create(parent: ViewGroup): GameViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.schedule_games, parent, false)
            return GameViewHolder(view)
        }
    }
 */