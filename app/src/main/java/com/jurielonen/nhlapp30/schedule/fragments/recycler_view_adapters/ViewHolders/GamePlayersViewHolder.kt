package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListSkaterStatsBinding
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlayer

class GamePlayersViewHolder(private val binding: GameListSkaterStatsBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(p: GamePlayer){
        with(binding){
            name = p.fullName
            with(p.stats) {
                toi = this!!.timeOnIce
                g = goals.toString()
                a = assists.toString()
                pm = plusMinus.toString()
                s = shots.toString()
                b = blocked.toString()
                pim = penaltyMinutes.toString()
                h = hits.toString()
                tv = takeaways.toString()
                gv = giveaways.toString()
            }
            executePendingBindings()
        }
    }
}