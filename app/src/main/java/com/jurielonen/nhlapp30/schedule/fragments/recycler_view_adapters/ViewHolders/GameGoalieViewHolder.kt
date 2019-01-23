package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListGoalieStatsBinding
import com.jurielonen.nhlapp30.schedule.fragments.model.GameGoalie

class GameGoalieViewHolder (private val binding: GameListGoalieStatsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(p: GameGoalie){
        with(binding){
            name = p.fullName
            toi = p.stats!!.timeOnIce
            shots = p.stats.shots.toString()
            saves = p.stats.saves.toString()
            svp = p.stats.savePercentage.toString()
            executePendingBindings()
        }
    }
}