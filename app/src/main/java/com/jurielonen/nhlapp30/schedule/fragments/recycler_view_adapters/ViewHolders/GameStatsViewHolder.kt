package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListStatsBinding
import com.jurielonen.nhlapp30.schedule.fragments.model.Stats

class GameStatsViewHolder(private val binding: GameListStatsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(stats: Stats){
        with(binding){
            stat = stats
            executePendingBindings()
        }
    }
}