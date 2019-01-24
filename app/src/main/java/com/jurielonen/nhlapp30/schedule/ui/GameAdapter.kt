package com.jurielonen.nhlapp30.schedule.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.model.Games

class GameAdapter: ListAdapter<Games, RecyclerView.ViewHolder>(GameDiffCallback()) {

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

private class GameDiffCallback : DiffUtil.ItemCallback<Games>(){
    override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean =
        oldItem.gamePk == newItem.gamePk

    override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean =
        oldItem == newItem
}