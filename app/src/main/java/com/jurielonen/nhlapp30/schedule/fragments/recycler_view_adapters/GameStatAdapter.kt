package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.R
import com.jurielonen.nhlapp30.schedule.fragments.model.Stats
import com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders.GameStatsViewHolder

class GameStatAdapter: ListAdapter<Stats, RecyclerView.ViewHolder>(StatsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GameStatsViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.game_list_stats, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = getItem(position).let{ stats ->
        with(holder){
            itemView.tag = stats
            (this as GameStatsViewHolder).bind(stats!!)
        }
    }


private class StatsDiffCallback : DiffUtil.ItemCallback<Stats>(){
    override fun areItemsTheSame(oldItem: Stats, newItem: Stats): Boolean =
        oldItem.statName == newItem.statName

    override fun areContentsTheSame(oldItem: Stats, newItem: Stats): Boolean =
        oldItem == newItem
}
}