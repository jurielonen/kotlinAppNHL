package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListSkaterHeaderBinding

class GamePlayersHeaderViewHolder(private val binding: GameListSkaterHeaderBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        with(binding){
            executePendingBindings()
        }
    }
}