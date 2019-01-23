package com.jurielonen.nhlapp30.schedule.ui


import androidx.recyclerview.widget.RecyclerView
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
