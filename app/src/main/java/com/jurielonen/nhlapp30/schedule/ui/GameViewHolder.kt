package com.jurielonen.nhlapp30.schedule.ui

import android.util.Log
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
import com.jurielonen.nhlapp30.schedule.NavigationSingleton
import com.jurielonen.nhlapp30.schedule.model.Games

class GameViewHolder(private val binding: ScheduleGamesBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Games) {
        with(binding) {
            gameData = game
            navigation = NavigationSingleton.getInstance()
            executePendingBindings()
        }
    }
}
