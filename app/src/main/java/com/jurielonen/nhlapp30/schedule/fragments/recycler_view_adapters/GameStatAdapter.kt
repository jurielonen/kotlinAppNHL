package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.fragments.model.GameTeamSkaterStats

/*
class GameStatAdapterListAdapter<Games, RecyclerView.ViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GameViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.schedule_games, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = getItem(position).let{ game ->
        with(holder){
            itemView.tag = game
            (this as GameViewHolder).bind(game!!)
        }
    }
}

private class PlayDiffCallback : DiffUtil.ItemCallback<GameTeamSkaterStats>(){
    override fun areItemsTheSame(oldItem: GameTeamSkaterStats, newItem: GameTeamSkaterStats): Boolean =
        oldItem.goals == newItem.goals

    override fun areContentsTheSame(oldItem: GameTeamSkaterStats, newItem: GameTeamSkaterStats): Boolean =
        oldItem == newItem
}
}*/