package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListGoalieHeaderBinding

class GameGoalieHeaderViewHolder (private val binding: GameListGoalieHeaderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        with(binding){
            executePendingBindings()
        }
    }
}