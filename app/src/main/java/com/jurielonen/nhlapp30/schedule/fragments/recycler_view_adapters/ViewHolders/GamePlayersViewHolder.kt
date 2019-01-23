package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListSkaterStatsBinding
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlayer

class GamePlayersViewHolder(private val binding: GameListSkaterStatsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(p: GamePlayer){
        with(binding){
            name = p.fullName
            toi = p.stats!!.timeOnIce
            g = p.stats.goals.toString()
            a = p.stats.assists.toString()
            pm = p.stats.plusMinus.toString()
            s = p.stats.shots.toString()
            b = p.stats.blocked.toString()
            pim = p.stats.penaltyMinutes.toString()
            h = p.stats.hits.toString()
            tv = p.stats.takeaways.toString()
            gv = p.stats.giveaways.toString()
            executePendingBindings()
        }
    }
}