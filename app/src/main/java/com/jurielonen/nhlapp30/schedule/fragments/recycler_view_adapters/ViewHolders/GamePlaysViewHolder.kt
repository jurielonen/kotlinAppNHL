package com.jurielonen.nhlapp30.schedule.fragments.recycler_view_adapters.ViewHolders

import androidx.recyclerview.widget.RecyclerView
import com.jurielonen.nhlapp30.databinding.GameListPlaysBinding
import com.jurielonen.nhlapp30.schedule.fragments.model.GamePlays

class GamePlaysViewHolder(private val binding: GameListPlaysBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(p: GamePlays){
        with(binding){
            play = p
            executePendingBindings()
        }
    }
}