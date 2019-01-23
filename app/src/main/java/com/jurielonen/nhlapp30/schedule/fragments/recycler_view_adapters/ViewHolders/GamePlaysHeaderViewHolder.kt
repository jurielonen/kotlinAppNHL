package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListPlaysHeaderBinding

class GamePlaysHeaderViewHolder(private val binding: GameListPlaysHeaderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(p: String){
        with(binding){
            when(p){
                "10000" -> period = "1.period"
                "20000" -> period = "2.period"
                else -> period = "3.period"
            }
            executePendingBindings()
        }
    }
}